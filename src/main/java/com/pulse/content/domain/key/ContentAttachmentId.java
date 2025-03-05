package com.pulse.content.domain.key;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ContentAttachmentId(Long id) {
    // custom constructor
    public static ContentAttachmentId of(Long id) {
        return new ContentAttachmentId(Objects.requireNonNull(id, "id must not be null"));
    }
}
