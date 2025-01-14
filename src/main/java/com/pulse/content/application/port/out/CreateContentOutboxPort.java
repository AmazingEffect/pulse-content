package com.pulse.content.application.port.out;

public interface CreateContentOutboxPort {

    Long saveContentOutboxEvent(ContentOutbox memberOutbox);

}
