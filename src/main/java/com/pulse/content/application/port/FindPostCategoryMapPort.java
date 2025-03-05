package com.pulse.content.application.port;

import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;

import java.util.List;

public interface FindPostCategoryMapPort {
    List<PostCategoryMap> findCategoriesByPostId(PostId postId);
}
