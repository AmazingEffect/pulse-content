package com.pulse.content.adapter.out.persistence.entity;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "content")
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentEntity extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "member_id")
    private Long memberId;

    // 파일 리스트(vo) --> List<Attachment>
    @ElementCollection
    @CollectionTable(name = "content_attachment", joinColumns = @JoinColumn(name = "content_id"))
    private List<ContentAttachmentEntity> contentAttachmentEntities;

    // 콘텐츠(vo)
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private ContentStatus contentStatus;

    @Enumerated(EnumType.STRING)
    private ContentVisibility contentVisibility;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentEntity that = (ContentEntity) o;
        return Objects.equals(contentId, that.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contentId);
    }
}
