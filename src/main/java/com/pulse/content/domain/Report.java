package com.pulse.content.domain;

import com.pulse.content.adapter.out.persistence.entity.TargetType;
import com.pulse.content.domain.key.ReportId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Report {

    private ReportId reportId;

    private TargetType targetType;  // 신고 대상 타입(POST, COMMENT)

    private Long targetId;          // 신고 대상 id

    private Long memberId;          // 신고한 회원 id

    private Long reportCategoryId;  // 신고 카테고리 id

    private String reason;          // 신고 사유 내용

}
