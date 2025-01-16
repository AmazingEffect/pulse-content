package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReactionEntity extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Long reactionId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "member_id")
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionEntity that = (ReactionEntity) o;
        return Objects.equals(reactionId, that.reactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reactionId);
    }
}