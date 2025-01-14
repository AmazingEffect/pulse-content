package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long memberId;

    // 해시태그 아이디 목록
    private Long hashTagIds;

    // 카테고리 아이디 목록
    private Long categoryIds;

    // 파일 리스트(vo) --> List<Attachment>
    private Long attachId;
    private String url;
    private Long fileId;

    // 콘텐츠(vo)
    private String title;
    private String text;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Enumerated(EnumType.STRING)
    private PostVisibility postVisibility;

}
