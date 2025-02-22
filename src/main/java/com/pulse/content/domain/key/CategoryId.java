package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record CategoryId(Long id) {

    // custom constructor
    public static CategoryId of(Long id){
        return new CategoryId(Objects.requireNonNull(id, "id must not be null"));
    }
}
