package com.pulse.content.event.spring;

import com.pulse.event_library.event.OutboxEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentCreateEvent implements OutboxEvent {

    private Long id;

    @Override
    public String getEventType() {
        return "ContentCreatedOutboxEvent";
    }

}
