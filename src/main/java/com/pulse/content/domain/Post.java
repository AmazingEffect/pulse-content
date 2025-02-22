package com.pulse.content.domain;

import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private PostId postId;
    private MemberId memberId;
    private Long hashTagIds;
    private Long categoryIds;
    private AttachId attachId;
    private String url;
    private FileId fileId;
    private String title;
    private String text;
    private PostStatus postStatus;
    private PostVisibility postVisibility;

    // factory method
    public static Post of(PostId postId, MemberId memberId, Long hashTagIds, Long categoryIds, AttachId attachId, String url, FileId fileId, String title, String text, PostStatus postStatus, PostVisibility postVisibility) {
        return Post.builder()
                .postId(postId)
                .memberId(memberId)
                .hashTagIds(hashTagIds)
                .categoryIds(categoryIds)
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .title(title)
                .text(text)
                .postStatus(postStatus)
                .postVisibility(postVisibility)
                .build();
    }
}

