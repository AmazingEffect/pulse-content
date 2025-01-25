package com.pulse.content.domain;


import com.pulse.content.adapter.out.persistence.entity.ReactionType;
import com.pulse.content.adapter.out.persistence.entity.TargetType;
import com.pulse.content.domain.key.ReactionId;

public record Reaction(
        ReactionId reactionId,
        TargetType targetType,
        Long targetId,
        Long memberId,
        ReactionType reactionType
) {}
