package com.pulse.content.application.port.out;

public interface FindContentOutboxPort {

    ContentOutbox findContentOutboxBy(Long payload, String eventType);

}
