package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ReportId(Long id) {

    // custom constructor
    public static ReportId of(Long id) {
        return new ReportId(Objects.requireNonNull(id, "id must not be null"));
    }
}
