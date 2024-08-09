package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공유하기
 * 게시물 공유 정보를 저장하는 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Share {

    private Long id;
    private Long memberId;
    private Post post;

}