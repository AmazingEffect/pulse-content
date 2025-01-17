package com.pulse.content.domain;

import com.pulse.content.domain.key.CategoryId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    private CategoryId id;

    private String name;    // 카테고리 이름

    private String text;    // 설명 내용

}
