package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.ContentHashTagMap;

import java.util.List;

public interface CreateContentHashTagMapPort {
    ContentHashTagMap create(ContentHashTagMap contentHashTagMap);
    List<ContentHashTagMap> createAll(List<ContentHashTagMap> contentHashTagMaps);
}
