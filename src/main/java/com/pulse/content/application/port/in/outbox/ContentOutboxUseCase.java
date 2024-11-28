package com.pulse.content.application.port.in.outbox;


import com.pulse.content.adapter.in.kafka.member.event.outbox.OutboxEvent;

public interface ContentOutboxUseCase {

    void markOutboxEventPending(OutboxEvent event);

    Long saveOutboxEvent(OutboxEvent event);

    void markOutboxEventSuccess(OutboxEvent event);

    void markOutboxEventFailed(OutboxEvent event);

    String getKafkaTopic(OutboxEvent event);

    void markOutboxEventProcessed(OutboxEvent event);

}
