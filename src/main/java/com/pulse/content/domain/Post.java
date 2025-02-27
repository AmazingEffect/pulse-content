package com.pulse.content.domain;

import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.domain.vo.PostAttachment;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private PostId postId;
    private MemberId memberId;
    private List<Long> hashTagIds;
    private List<PostCategoryMap> postCategories;
    private List<PostAttachment> postAttachments;
    private ContentDetail contentDetail;
    private PostStatus postStatus;
    private PostVisibility postVisibility;

    // factory method
    public static Post of(PostId postId, MemberId memberId, List<Long> hashTagIds, List<PostCategoryMap> postCategories, List<PostAttachment> postAttachments, ContentDetail contentDetail, PostStatus postStatus, PostVisibility postVisibility) {
        return Post.builder()
                .postId(postId)
                .memberId(memberId)
                .hashTagIds(hashTagIds)
                .postCategories(postCategories)
                .postAttachments(postAttachments)
                .contentDetail(contentDetail)
                .postStatus(postStatus)
                .postVisibility(postVisibility)
                .build();
    }
}

