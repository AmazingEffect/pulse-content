package com.pulse.content.domain;

import com.pulse.content.domain.key.CommentId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private CommentId id;

    private Long postId;    // 게시글 id

    private Long memberId;  // 작성자 회원 id

    private String title;   // 게시글 내용 제목

    private String text;    // 게시글 내용 텍스트

}
