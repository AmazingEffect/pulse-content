package com.pulse.content.domain.key;

import java.util.Objects;

public record QuoteId(Long id) {

    // custom constructor
    public static QuoteId of(Long id) {
        return new QuoteId(Objects.requireNonNull(id, "id must not be null"));
    }
}
