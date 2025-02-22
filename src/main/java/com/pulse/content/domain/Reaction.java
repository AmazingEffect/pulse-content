package com.pulse.content.domain;


import com.pulse.content.common.enumerate.ReactionType;
import com.pulse.content.common.enumerate.TargetType;
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

    // factory method
    public static Reaction of(ReactionId reactionId, TargetType targetType, Long targetId, Long memberId, ReactionType reactionType) {
        return Reaction.builder()
                .reactionId(reactionId)
                .targetType(targetType)
                .targetId(targetId)
                .memberId(memberId)
                .reactionType(reactionType)
                .build();
    }
}
