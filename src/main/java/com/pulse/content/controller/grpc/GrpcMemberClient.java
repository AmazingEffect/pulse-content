package com.pulse.content.controller.grpc;

import com.pulse.content.config.trace.annotation.Traceable;
import com.pulse.content.config.trace.aop.TracingAspect;
import com.pulse.member.grpc.MemberProto;
import com.pulse.member.grpc.MemberServiceGrpc;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import org.springframework.stereotype.Component;

/**
 * gRPC 클라이언트
 * GrpcClient 클래스는 gRPC 클라이언트를 구현한 것으로, 다른 서버 또는 같은 서버 내에서 gRPC 서버 메서드를 호출하는 데 사용됩니다.
 * 이 클래스는 애플리케이션 내에서 gRPC 서버에 요청을 보내는 역할을 합니다.
 */
@Component
public class GrpcMemberClient {

    private final MemberServiceGrpc.MemberServiceBlockingStub blockingStub;
    private final TracingAspect tracingAspect;

    // gRPC 서버에 연결 (생성자)
    public GrpcMemberClient(TracingAspect tracingAspect) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        blockingStub = MemberServiceGrpc.newBlockingStub(channel);
        this.tracingAspect = tracingAspect;
    }

    /**
     * id로 회원 정보 받아오기
     * zeroPayload 정책으로 id만 보내서 데이터를 받아옴
     *
     * @param id - 회원 id
     */
    @Traceable
    public MemberProto.MemberRetrieveResponse getMemberById(Long id, Context context) {
        GrpcRequestResult result = createGrpcRequest(id, context);
        return result.stubWithHeaders().getMemberById(result.request());
    }

    /**
     * id로 닉네임 받아오기
     * zeroPayload 정책으로 id만 보내서 데이터를 받아옴
     *
     * @param id      - 회원 id
     * @param context - 현재 컨텍스트
     */
    @Traceable
    public MemberProto.MemberNicknameResponse getNicknameById(Long id, Context context) {
        GrpcRequestResult result = createGrpcRequest(id, context);
        return result.stubWithHeaders().getNicknameById(result.request());
    }

    /**
     * id로 프로필 이미지 url 받아오기
     * zeroPayload 정책으로 id만 보내서 데이터를 받아옴
     *
     * @param id      - 회원 id
     * @param context - 현재 컨텍스트
     */
    @Traceable
    public MemberProto.MemberProfileImageUrlResponse getProfileImageUrl(Long id, Context context) {
        GrpcRequestResult result = createGrpcRequest(id, context);
        return result.stubWithHeaders().getProfileImageUrlById(result.request());
    }

    /**
     * Grpc Request 생성
     * Metadata를 헤더로 추가하여 요청 (traceparent 헤더 주입)
     *
     * @param id
     * @param context
     * @return
     */
    private GrpcRequestResult createGrpcRequest(Long id, Context context) {
        // 1. Metadata 생성 (aspect 내부의 createMetadata 메서드 호출)
        Metadata metadata = tracingAspect.createMetadata(context);

        // 2. gRPC 요청을 위한 MemberIdRequest 객체 생성
        MemberProto.MemberIdRequest request = MemberProto.MemberIdRequest.newBuilder()
                .setId(id)
                .build();

        // 3. Metadata를 헤더로 추가하여 요청 (traceparent 헤더 주입)
        Channel interceptedChannel = ClientInterceptors.intercept(
                blockingStub.getChannel(), MetadataUtils.newAttachHeadersInterceptor(metadata));
        MemberServiceGrpc.MemberServiceBlockingStub stubWithHeaders = MemberServiceGrpc.newBlockingStub(interceptedChannel);

        // 4. gRPC 요청 결과 반환
        return new GrpcRequestResult(request, stubWithHeaders);
    }

    /**
     * gRPC 요청 결과 dto
     *
     * @param request
     * @param stubWithHeaders
     */
    private record GrpcRequestResult(MemberProto.MemberIdRequest request,
                                     MemberServiceGrpc.MemberServiceBlockingStub stubWithHeaders) {
    }

}
