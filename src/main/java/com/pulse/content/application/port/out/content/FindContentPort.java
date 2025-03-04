package com.pulse.content.application.port.out.content;

import com.pulse.content.domain.Content;
import com.pulse.content.domain.key.ContentId;

public interface FindContentPort {
    Content findContent(ContentId contentId);
}
