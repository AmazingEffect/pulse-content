package com.pulse.content.adapter.out.persistence.entity.vo;

import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.common.enumerate.AttachmentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "content_attachment")
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_attachment_id")
    private Long contentAttachmentId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentEntity contentEntity;

    // factory method
    public static ContentAttachmentEntity of(
            Long attachId, String url, Long fileId, String contentType,
            Long size, AttachmentType attachmentType
    ) {
        return ContentAttachmentEntity.builder()
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
        ContentAttachmentEntity that = (ContentAttachmentEntity) o;
        return Objects.equals(contentAttachmentId, that.contentAttachmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentAttachmentId);
    }
}
