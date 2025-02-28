package com.pulse.content.adapter.out.persistence.entity;

import com.pulse.content.adapter.out.persistence.entity.vo.PostAttachmentEntity;
import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "post")
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "member_id")
    private Long memberId;

    // 파일 리스트(vo) --> List<Attachment>
    @ElementCollection
    @CollectionTable(name = "post_attachment", joinColumns = @JoinColumn(name = "post_id"))
    private List<PostAttachmentEntity> postAttachmentEntities;

    // 콘텐츠(vo)
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Enumerated(EnumType.STRING)
    private PostVisibility postVisibility;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(postId);
    }
}
