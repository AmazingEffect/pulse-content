package com.pulse.content.application.port.in.map;

import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostHashTag;

import java.util.List;

public interface FindPostHashTagMapUseCase {
    List<PostHashTag> findHashTagsByPostId(PostId postId);
}
