package com.pulse.content.domain;

import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.mapping.PostCategory;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private PostId postId;
    private MemberId memberId;
    private List<Long> hashTagIds;
    private List<PostCategory> postCategories;
    private AttachId attachId;
    private FileId fileId;
    private String url;
    private String title;
    private String text;
    private PostStatus postStatus;
    private PostVisibility postVisibility;

    // factory method
    public static Post of(PostId postId, MemberId memberId, List<Long> hashTagIds, List<PostCategory> postCategories, AttachId attachId, String url, FileId fileId, String title, String text, PostStatus postStatus, PostVisibility postVisibility) {
        return Post.builder()
                .postId(postId)
                .memberId(memberId)
                .hashTagIds(hashTagIds)
                .postCategories(postCategories)
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .title(title)
                .text(text)
                .postStatus(postStatus)
                .postVisibility(postVisibility)
                .build();
    }

    public static Post ofEmptyPostCategories(PostId postId, MemberId memberId, List<Long> hashTagIds, AttachId attachId, String url, FileId fileId, String title, String text, PostStatus postStatus, PostVisibility postVisibility) {
        return Post.builder()
                .postId(postId)
                .memberId(memberId)
                .hashTagIds(hashTagIds)
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .title(title)
                .text(text)
                .postStatus(postStatus)
                .postVisibility(postVisibility)
                .build();
    }

    public void addPostCategories(PostCategory postCategory) {
        this.postCategories.add(postCategory);
    }
}

