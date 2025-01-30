package com.pulse.content.domain;

import com.pulse.content.adapter.out.persistence.entity.TargetType;
import com.pulse.content.domain.key.CategoryId;
import com.pulse.content.domain.key.ReportId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Report {

    private ReportId reportId;

    private TargetType targetType;  // 신고 대상 타입(POST, COMMENT)

    private Long targetId;          // 신고 대상 id

    private Long memberId;          // 신고한 회원 id

    private CategoryId reportCategoryId;  // 신고 카테고리 id

    private String reason;          // 신고 사유 내용

    // factory method
    public static Report of(ReportId reportId, TargetType targetType, Long targetId, Long memberId, CategoryId reportCategoryId, String reason) {
        return Report.builder()
                .reportId(reportId)
                .targetType(targetType)
                .targetId(targetId)
                .memberId(memberId)
                .reportCategoryId(reportCategoryId)
                .reason(reason)
                .build();
    }
}
