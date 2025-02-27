package com.pulse.content.domain.vo;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentDetailEntity;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentDetail that = (ContentDetail) o;
        return Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text);
    }
}
