package com.pulse.content.domain;


import com.pulse.content.adapter.out.persistence.entity.ReactionType;
import com.pulse.content.adapter.out.persistence.entity.TargetType;

public record Reaction(
        ReactionId reactionId,
        TargetType targetType,
        Long targetId,
        Long memberId,
        ReactionType reactionType
) {}
