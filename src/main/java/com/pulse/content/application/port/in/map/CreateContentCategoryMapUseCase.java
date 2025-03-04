package com.pulse.content.application.port.in.map;

import com.pulse.content.domain.map.ContentCategoryMap;

public interface CreateContentCategoryMapUseCase {
    ContentCategoryMap create(ContentCategoryMap contentCategoryMap);
}
