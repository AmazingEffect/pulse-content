package com.pulse.content.domain;

import com.pulse.content.domain.key.CategoryId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    private CategoryId id;

    private String name;    // 카테고리 이름

    private String text;    // 설명 내용

    // factory method
    public static Category of(CategoryId id, String name, String text) {
        return Category.builder()
                .id(id)
                .name(name)
                .text(text)
                .build();
    }
}
