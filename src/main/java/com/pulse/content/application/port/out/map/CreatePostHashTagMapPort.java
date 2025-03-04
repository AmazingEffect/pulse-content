package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.PostHashTagMap;

public interface CreatePostHashTagMapPort {
    PostHashTagMap create(PostHashTagMap postHashTagMap);
}
