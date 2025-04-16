package com.pulse.content.domain.key;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MemberId(@NotNull Long id) {}

