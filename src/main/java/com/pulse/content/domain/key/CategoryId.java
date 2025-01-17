package com.pulse.content.domain.key;

import java.util.Objects;

public record CategoryId(Long id) {

    // custom constructor
    public static CategoryId of(Long id){
        return new CategoryId(Objects.requireNonNull(id, "id must not be null"));
    }
}
