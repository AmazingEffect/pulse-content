package com.pulse.content.domain.vo;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentDetail {

    private String title;
    private String text;

    // factory method
    public static ContentDetail of(String title, String text) {
        return ContentDetail.builder()
                .title(title)
                .text(text)
                .build();
    }
}
