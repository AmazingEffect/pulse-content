package com.pulse.content.application.port.out;

import com.pulse.content.domain.Post;

public interface CreateContentPort {
    Post create(Post post);
}
