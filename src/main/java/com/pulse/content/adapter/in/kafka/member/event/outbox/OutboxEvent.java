package com.pulse.content.adapter.in.kafka.member.event.outbox;

public interface OutboxEvent {

    Long getPayload();

    String getEventType();

}
