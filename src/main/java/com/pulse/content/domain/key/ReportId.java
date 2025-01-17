package com.pulse.content.domain.key;

import java.util.Objects;

public record ReportId(Long id) {

    // custom constructor
    public static ReportId of(Long id) {
        return new ReportId(Objects.requireNonNull(id, "id must not be null"));
    }
}
