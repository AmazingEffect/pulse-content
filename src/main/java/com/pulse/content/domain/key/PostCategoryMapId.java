package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record PostCategoryMapId(Long id) {
    // custom constructor
    public static PostCategoryMapId of(Long id){
        return new PostCategoryMapId(Objects.requireNonNull(id, "id must not be null"));
    }
}
