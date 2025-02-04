package com.pulse.content.domain;


import com.pulse.content.adapter.out.persistence.entity.ReactionType;
import com.pulse.content.adapter.out.persistence.entity.TargetType;
import com.pulse.content.domain.key.ReactionId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Reaction {
    private ReactionId reactionId;
    private TargetType targetType;
    private Long targetId;
    private Long memberId;
    private ReactionType reactionType;
}
