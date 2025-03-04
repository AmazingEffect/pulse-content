package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ContentHashTagMapId(Long id) {
    public static ContentHashTagMapId of(Long id){
        return new ContentHashTagMapId(Objects.requireNonNull(id, "id must not be null"));
    }
}
