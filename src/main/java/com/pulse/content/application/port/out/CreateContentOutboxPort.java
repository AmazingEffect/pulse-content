package com.pulse.content.application.port.out;

import com.pulse.content.domain.ContentOutbox;

public interface CreateContentOutboxPort {

    Long saveContentOutboxEvent(ContentOutbox memberOutbox);

}
