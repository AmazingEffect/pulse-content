package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.ContentHashTagMap;

public interface CreateContentHashTagMapPort {
    ContentHashTagMap create(ContentHashTagMap contentHashTagMap);
}
