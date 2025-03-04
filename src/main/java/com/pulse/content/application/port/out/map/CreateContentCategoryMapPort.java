package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.domain.map.ContentHashTagMap;

import java.util.List;

public interface CreateContentCategoryMapPort {
    ContentCategoryMap create(ContentCategoryMap contentCategoryMap);
    List<ContentCategoryMap> createAll(List<ContentCategoryMap> contentCategoryMaps);
}
