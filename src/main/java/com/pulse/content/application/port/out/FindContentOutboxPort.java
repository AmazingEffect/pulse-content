package com.pulse.content.application.port.out;

import com.pulse.content.domain.ContentOutbox;

public interface FindContentOutboxPort {

    ContentOutbox findContentOutboxBy(Long payload, String eventType);

}
