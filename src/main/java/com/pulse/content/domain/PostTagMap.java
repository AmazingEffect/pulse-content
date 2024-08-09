package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 태그 매핑
 * 게시물과 태그 간의 관계를 나타내는 매핑 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostTagMap {

    private Long id;
    private Post post;
    private Tag tag;

}
