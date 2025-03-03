package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.map.PostCategoryMap;

public interface CreatePostCategoryMapPort {
    PostCategoryMap create(PostCategoryMap postCategoryMap);
}
