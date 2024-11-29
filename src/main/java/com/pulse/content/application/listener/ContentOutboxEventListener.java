package com.pulse.content.application.listener;

import com.pulse.content.adapter.in.kafka.member.event.outbox.OutboxEvent;
import com.pulse.content.application.port.in.outbox.ContentOutboxUseCase;
import com.pulse.content.application.port.out.kafka.KafkaProducerPort;
import com.pulse.content.config.trace.annotation.TraceOutboxEvent;
import io.opentelemetry.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Outbox 테이블과 관련된 스프링 이벤트를 처리하는 리스너
 */
@RequiredArgsConstructor
@Component
public class ContentOutboxEventListener {

    private final ContentOutboxUseCase memberOutboxUseCase;
    private final KafkaProducerPort kafkaProducerPort;

    /**
     * @param event Outbox 이벤트
     * @apiNote 이벤트가 발행되면 트랜잭션이 커밋되기 전에 Outbox 테이블에 저장하고 커밋한다.
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @TraceOutboxEvent(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleOutboxEvent(OutboxEvent event) {
        // Outbox 테이블에 이벤트를 저장한다.
        memberOutboxUseCase.saveOutboxEvent(event);
    }

    /**
     * @param event 전송할 Outbox 이벤트
     * @apiNote 트랜잭션이 성공적으로 커밋되면 Kafka 메시지를 전송합니다.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @TraceOutboxEvent(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void sendToKafka(OutboxEvent event) {
        try {
            // 1. 메시지로 보낼 payload와 전송할 Kafka의 토픽 정보를 가져옵니다.
            Long message = event.getPayload();
            String topic = memberOutboxUseCase.getKafkaTopic(event);

            // 2. 추출한 토픽에 Kafka 메시지를 전송합니다.
            kafkaProducerPort.sendWithRetryWithoutKey(topic, String.valueOf(message), Context.current());

            // 3. Outbox 이벤트를 처리 대기 상태로 변경합니다.
            memberOutboxUseCase.markOutboxEventPending(event);
        } catch (Exception e) {
            // exception: 예외 발생 시 Outbox 이벤트를 실패 상태로 변경합니다.
            memberOutboxUseCase.markOutboxEventFailed(event);
        }
    }

}