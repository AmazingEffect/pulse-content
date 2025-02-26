package com.pulse.content.adapter.out.persistence.entity;

import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "member_id")
    private Long memberId;

    // 해시태그 아이디 목록
//    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
//    private List<PostHashTagMapEntity> postHashTagMapEntities = new ArrayList<>();
//    private List<Long> hashTagIds = new;

    // 카테고리 아이디 목록
    private Long categoryIds;

    // 파일 리스트(vo) --> List<Attachment>
    @Column(name = "attach_id")
    private Long attachId;
    @Column(name = "url")
    private String url;
    @Column(name = "file_id")
    private Long fileId;

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
