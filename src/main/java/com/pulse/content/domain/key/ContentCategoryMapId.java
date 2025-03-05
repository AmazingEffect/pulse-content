package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ContentCategoryMapId(Long id) {
    // custom constructor
    public static ContentCategoryMapId of(Long id){
        return new ContentCategoryMapId(Objects.requireNonNull(id, "id must not be null"));
    }
}
