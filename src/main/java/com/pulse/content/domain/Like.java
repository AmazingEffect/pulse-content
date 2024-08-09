package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좋아요 (예약어 때문에 s 붙임)
 * 게시물 및 댓글에 대한 좋아요 정보를 저장하는 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Like {

    private Long id;
    private Long memberId;
    private Post post;
    private Comment comment;

}
