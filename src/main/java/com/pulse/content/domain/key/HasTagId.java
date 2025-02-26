package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record HasTagId(Long id) {

    // custom constructor
    public static HasTagId of(Long id){
        return new HasTagId(Objects.requireNonNull(id, "id must not be null"));
    }
}
