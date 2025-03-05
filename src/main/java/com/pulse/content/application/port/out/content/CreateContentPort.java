package com.pulse.content.application.port.out.content;

import com.pulse.content.domain.Content;

public interface CreateContentPort {
    Content create(Content content);
}
