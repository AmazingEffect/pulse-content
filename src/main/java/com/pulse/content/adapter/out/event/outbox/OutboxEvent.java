package com.pulse.content.adapter.out.event.outbox;

public interface OutboxEvent {

    Long getPayload();

    String getEventType();

}
