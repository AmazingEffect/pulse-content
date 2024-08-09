package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 북마크
 * 사용자가 게시물을 북마크할 수 있는 기능을 위한 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Bookmark {

    private Long id;
    private Long memberId;
    private Post post;

}