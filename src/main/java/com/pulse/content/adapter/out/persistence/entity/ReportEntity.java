package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "report")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TargetType targetType;  // 신고 대상 타입(POST, COMMENT)
    private Long targetId;          // 신고 대상 id
    private Long memberId;          // 신고한 회원 id
    private Long reportCategoryId;  // 신고 카테고리 id
    private String reason;          // 신고 사유 내용
}
