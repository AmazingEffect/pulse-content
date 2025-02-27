package com.pulse.content.domain;

import com.pulse.content.domain.key.CommentId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private CommentId commentId;
    private PostId postId;    // 게시글 id
    private MemberId memberId;  // 작성자 회원 id
    private String title;   // 게시글 내용 제목
    private String text;    // 게시글 내용 텍스트

    // factory method
    public static Comment of(PostId postId, MemberId memberId, String title, String text) {
        return Comment.builder()
                .postId(postId)
                .memberId(memberId)
                .title(title)
                .text(text)
                .build();
    }
}
