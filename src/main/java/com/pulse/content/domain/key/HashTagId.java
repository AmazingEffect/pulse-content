package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record HashTagId(Long id) {

    // custom constructor
    public static HashTagId of(Long id){
        return new HashTagId(Objects.requireNonNull(id, "id must not be null"));
    }
}
