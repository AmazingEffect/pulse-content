package com.pulse.content.domain;

import com.pulse.content.domain.key.CategoryId;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    private CategoryId categoryId;
    private String name;    // 카테고리 이름
    private String text;    // 설명 내용

    // factory method
    public static Category of(String name, String text) {
        return Category.builder()
                .name(name)
                .text(text)
                .build();
    }
}
