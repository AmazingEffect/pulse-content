package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record PostHashTagId(Long id) {
    public static PostHashTagId of(Long id){
        return new PostHashTagId(Objects.requireNonNull(id, "id must not be null"));
    }
}
