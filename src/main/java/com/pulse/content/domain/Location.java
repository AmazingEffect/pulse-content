package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 위치
 * 게시물의 위치 정보를 저장하는 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Location {

    private Long id;
    private Post post;
    private String location;

}