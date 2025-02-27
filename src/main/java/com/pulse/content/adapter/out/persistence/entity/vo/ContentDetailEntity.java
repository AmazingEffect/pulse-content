package com.pulse.content.adapter.out.persistence.entity.vo;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentDetailEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    // factory method
    public static ContentDetailEntity of(String title, String text) {
        return ContentDetailEntity.builder()
                .title(title)
                .text(text)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentDetailEntity that = (ContentDetailEntity) o;
        return Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text);
    }
}
