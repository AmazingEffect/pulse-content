package com.pulse.content.controller.grpc;

import com.pulse.content.util.TraceUtil;
import com.pulse.member.grpc.MemberProto;
import com.pulse.member.grpc.MemberServiceGrpc;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * gRPC 클라이언트
 * GrpcClient 클래스는 gRPC 클라이언트를 구현한 것으로, 다른 서버 또는 같은 서버 내에서 gRPC 서버 메서드를 호출하는 데 사용됩니다.
 * 이 클래스는 애플리케이션 내에서 gRPC 서버에 요청을 보내는 역할을 합니다.
 */
@Component
public class GrpcClient {

    private final MemberServiceGrpc.MemberServiceBlockingStub blockingStub;
    private final Tracer tracer = GlobalOpenTelemetry.getTracer("grpc-client");

    // gRPC 요청을 위한 TextMapSetter
    private static final TextMapSetter<Metadata> setter = new TextMapSetter<Metadata>() {
        @Override
        public void set(Metadata carrier, String key, String value) {
            carrier.put(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER), value);
        }
    };

    // gRPC 서버에 연결 (생성자)
    public GrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        blockingStub = MemberServiceGrpc.newBlockingStub(channel);
    }

    /**
     * id로 회원 정보 받아오기
     * zeroPayload 정책으로 id만 보내서 데이터를 받아옴
     *
     * @param id
     * @return
     */
    public MemberProto.MemberResponse getMemberById(Long id, Context context) {
        // 1. Span 생성
        Span span = tracer.spanBuilder("grpc-call").setParent(context).startSpan();

        // 2. Span을 현재 컨텍스트에 설정
        try (Scope scope = span.makeCurrent()) {
            Metadata metadata = new Metadata();
            GlobalOpenTelemetry.getPropagators().getTextMapPropagator().inject(context, metadata, setter);

            // gRPC 요청을 위한 MemberIdRequest 객체 생성
            MemberProto.MemberIdRequest request = MemberProto.MemberIdRequest.newBuilder()
                    .setId(id)
                    .build();

            // Metadata를 헤더로 추가하여 요청 (traceparent 헤더 주입)
            Channel interceptedChannel = ClientInterceptors.intercept(
                    blockingStub.getChannel(), MetadataUtils.newAttachHeadersInterceptor(metadata));
            MemberServiceGrpc.MemberServiceBlockingStub stubWithHeaders = MemberServiceGrpc.newBlockingStub(interceptedChannel);

            // gRPC 서버에 요청을 보내고 응답을 받아옴
            return stubWithHeaders.getMemberById(request);
        } finally {
            // 3. Span 종료
            span.end();
        }
    }
}
