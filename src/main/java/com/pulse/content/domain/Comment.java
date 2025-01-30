package com.pulse.content.domain;

import com.pulse.content.domain.key.CommentId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private CommentId id;

    private PostId postId;    // 게시글 id

    private MemberId memberId;  // 작성자 회원 id

    private String title;   // 게시글 내용 제목

    private String text;    // 게시글 내용 텍스트

    // factory method
    public static Comment of(CommentId id, PostId postId, MemberId memberId, String title, String text) {
        return Comment.builder()
                .id(id)
                .postId(postId)
                .memberId(memberId)
                .title(title)
                .text(text)
                .build();
    }
}
