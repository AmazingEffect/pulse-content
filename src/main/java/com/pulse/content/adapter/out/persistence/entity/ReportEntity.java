package com.pulse.content.adapter.out.persistence.entity;

import com.pulse.content.common.enumerate.TargetType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "report")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private TargetType targetType;  // 신고 대상 타입(POST, COMMENT)

    @Column(name = "target_id")
    private Long targetId;          // 신고 대상 id

    @Column(name = "member_id")
    private Long memberId;          // 신고한 회원 id

    @Column(name = "report_category_id")
    private Long reportCategoryId;  // 신고 카테고리 id

    @Column(name = "reason")
    private String reason;          // 신고 사유 내용

    // factory method
    public static ReportEntity of(Long id, TargetType targetType, Long targetId, Long memberId, Long reportCategoryId, String reason) {
        return ReportEntity.builder()
                .id(id)
                .targetType(targetType)
                .targetId(targetId)
                .memberId(memberId)
                .reportCategoryId(reportCategoryId)
                .reason(reason)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
