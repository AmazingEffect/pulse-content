package com.pulse.content.controller.grpc;

import com.pulse.member.grpc.MemberProto;
import com.pulse.member.grpc.MemberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

/**
 * gRPC 클라이언트
 * GrpcClient 클래스는 gRPC 클라이언트를 구현한 것으로, 다른 서버 또는 같은 서버 내에서 gRPC 서버 메서드를 호출하는 데 사용됩니다.
 * 이 클래스는 애플리케이션 내에서 gRPC 서버에 요청을 보내는 역할을 합니다.
 */
@Component
public class GrpcClient {

    private final MemberServiceGrpc.MemberServiceBlockingStub blockingStub;

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
    public MemberProto.MemberResponse getMemberById(Long id) {
        MemberProto.MemberIdRequest request = MemberProto.MemberIdRequest.newBuilder()
                .setId(id)
                .build();
        return blockingStub.getMemberById(request);
    }

}