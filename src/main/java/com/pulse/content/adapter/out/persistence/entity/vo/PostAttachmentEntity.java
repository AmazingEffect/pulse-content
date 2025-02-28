package com.pulse.content.adapter.out.persistence.entity.vo;

import com.pulse.content.common.enumerate.AttachmentType;
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

    @Column(name = "content_type")
    private String contentType;         // MIME

    @Column(name = "size")
    private Long size;                  // 첨부 파일 size

    @Enumerated(EnumType.STRING)
    @Column(name = "attachment_type")
    private AttachmentType attachmentType;        // 첨부 파일 분류



    // factory method
    public static PostAttachmentEntity of(
            Long attachId, String url, Long fileId, String contentType,
            Long size, AttachmentType attachmentType
    ) {
        return PostAttachmentEntity.builder()
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .contentType(contentType)
                .size(size)
                .attachmentType(attachmentType)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostAttachmentEntity that = (PostAttachmentEntity) o;
        return Objects.equals(attachId, that.attachId) && Objects.equals(url, that.url) && Objects.equals(fileId, that.fileId) && Objects.equals(contentType, that.contentType) && Objects.equals(size, that.size) && attachmentType == that.attachmentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachId, url, fileId, contentType, size, attachmentType);
    }
}
