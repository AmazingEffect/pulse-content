package com.pulse.content.domain.key;

import java.util.Objects;

public record CommentId(Long id) {

    // custom constructor
    public static CommentId of(Long id){
        return new CommentId(Objects.requireNonNull(id, "id must not be null"));
    }
}
