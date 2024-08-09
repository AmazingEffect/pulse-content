package com.pulse.content.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 태그
 * 태그 정보를 저장하는 엔티티입니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tag {

    private Long id;
    private String name;

}
