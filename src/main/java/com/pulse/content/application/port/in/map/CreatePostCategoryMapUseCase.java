package com.pulse.content.application.port.in.map;

import com.pulse.content.domain.map.PostCategoryMap;

public interface CreatePostCategoryMapUseCase {
    PostCategoryMap create(PostCategoryMap postCategoryMap);
}
