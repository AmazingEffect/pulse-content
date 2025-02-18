package com.pulse.content.application.port;

import com.pulse.content.adapter.in.web.dto.response.ContentResponseDTO;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;

public interface FindContentPort {
    Post findContent(PostId postId);
}
