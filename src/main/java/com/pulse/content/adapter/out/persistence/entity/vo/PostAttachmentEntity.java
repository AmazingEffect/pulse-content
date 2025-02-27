package com.pulse.content.adapter.out.persistence.entity.vo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Embeddable
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAttachmentEntity {

    @Column(name = "attach_id")
    private Long attachId;

    @Column(name = "url")
    private String url;

    @Column(name = "file_id")
    private Long fileId;

    // factory method
    public static PostAttachmentEntity of(Long attachId, String url, Long fileId) {
        return PostAttachmentEntity.builder()
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostAttachmentEntity that = (PostAttachmentEntity) o;
        return Objects.equals(attachId, that.attachId) && Objects.equals(url, that.url) && Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachId, url, fileId);
    }
}
