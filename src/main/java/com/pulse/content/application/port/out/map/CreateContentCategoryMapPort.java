package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.ContentCategoryMap;

public interface CreateContentCategoryMapPort {
    ContentCategoryMap create(ContentCategoryMap contentCategoryMap);
}
