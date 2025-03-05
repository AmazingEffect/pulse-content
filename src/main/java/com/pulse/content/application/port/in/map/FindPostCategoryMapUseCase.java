package com.pulse.content.application.port.in.map;

import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;

import java.util.List;

public interface FindPostCategoryMapUseCase {
    List<PostCategoryMap> findCategoriesByPostId(PostId postId);
}
