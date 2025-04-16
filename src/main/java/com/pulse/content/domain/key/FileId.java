package com.pulse.content.domain.key;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FileId(@NotNull Long id) {}

