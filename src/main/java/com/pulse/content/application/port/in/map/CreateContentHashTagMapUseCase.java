package com.pulse.content.application.port.in.map;

import com.pulse.content.domain.map.ContentHashTagMap;

public interface CreateContentHashTagMapUseCase {
    ContentHashTagMap create(ContentHashTagMap contentHashTagMap);
}
