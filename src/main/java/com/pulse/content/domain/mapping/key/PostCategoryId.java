package com.pulse.content.domain.mapping.key;

import com.pulse.content.domain.key.CategoryId;
import lombok.Builder;

import java.util.Objects;

@Builder
public record PostCategoryId(Long id) {
    // custom constructor
    public static PostCategoryId of(Long id){
        return new PostCategoryId(Objects.requireNonNull(id, "id must not be null"));
    }
}
