package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentHashTagMap;

import java.util.List;

public interface FindContentHashTagMapPort {
    List<ContentHashTagMap> findByContentId(Long contentId);
}
