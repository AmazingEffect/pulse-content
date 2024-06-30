package com.pulse.content.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.content.controller.grpc.GrpcClient;
import com.pulse.content.event.spring.MemberCreateEvent;
import com.pulse.event_library.event.OutboxEvent;
import com.pulse.event_library.service.OutboxService;
import com.pulse.member.grpc.MemberProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberMessageListener {

    private final OutboxService outboxService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GrpcClient grpcClient;


    /**
     * 유저가 생성되면 이벤트를 수신하고
     * gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
     *
     * @param record
     * @param acknowledgment
     * @param partition
     * @param offset
     */
    @KafkaListener(
            topics = "member-created-outbox",
            groupId = "content-group-external",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenExternal(
            ConsumerRecord<String, String> record,
            Acknowledgment acknowledgment,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) throws JsonProcessingException {
        try {
            log.info("Received message: {} from partition: {} with offset: {}", record.value(), partition, offset);

            // JSON 데이터를 OutboxEvent로 변환한다.
            String jsonValue = record.value();
            OutboxEvent event = objectMapper.readValue(jsonValue, MemberCreateEvent.class);

            // gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
            MemberProto.MemberResponse result = grpcClient.getMemberById(event.getId());
            log.info(String.valueOf(result));
        } catch (Exception e) {
            log.error("Error occurred while processing message: {}", e.getMessage());
            throw e;
        }

        acknowledgment.acknowledge();
    }

}
