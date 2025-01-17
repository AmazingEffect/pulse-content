package com.pulse.content.domain.key;

import java.util.Objects;

public record HasTagId(Long id) {

    // custom constructor
    public static HasTagId of(Long id){
        return new HasTagId(Objects.requireNonNull(id, "id must not be null"));
    }
}
