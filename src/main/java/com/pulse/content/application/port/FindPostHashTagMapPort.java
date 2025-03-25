package com.pulse.content.application.port;

import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostHashTag;

import java.util.List;

public interface FindPostHashTagMapPort {
    List<PostHashTag> findHashTagsByPostId(PostId postId);
}
