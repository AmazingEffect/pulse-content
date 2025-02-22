package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record QuoteId(Long id) {

    // custom constructor
    public static QuoteId of(Long id) {
        return new QuoteId(Objects.requireNonNull(id, "id must not be null"));
    }
}
