package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record PostHashTagMapId(Long id) {
    public static PostHashTagMapId of(Long id){
        return new PostHashTagMapId(Objects.requireNonNull(id, "id must not be null"));
    }
}
