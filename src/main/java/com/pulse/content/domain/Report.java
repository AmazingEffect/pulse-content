package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 신고 엔티티
 * 게시물 및 댓글에 대한 신고 정보를 저장하는 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Report {

    private Long id;
    private Long reportedById;
    private Post post;
    private Comment comment;
    private String reason;

}
