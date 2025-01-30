package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "comment")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long postId;    // 게시글 id

    @Column(name = "member_id")
    private Long memberId;  // 작성자 회원 id

    @Column(name = "title")
    private String title;   // 게시글 내용 제목

    @Column(name = "text")
    private String text;    // 게시글 내용 텍스트

    // factory method
    public static CommentEntity of(Long id, Long postId, Long memberId, String title, String text) {
        return CommentEntity.builder()
                .id(id)
                .postId(postId)
                .memberId(memberId)
                .title(title)
                .text(text)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
