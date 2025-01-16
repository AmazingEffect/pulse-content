package com.pulse.content.domain;

import com.pulse.content.adapter.out.persistence.entity.PostStatus;
import com.pulse.content.adapter.out.persistence.entity.PostVisibility;

public record Post(
        PostId postId,
        MemberId memberId,
        Long hashTagIds,
        Long categoryIds,
        AttachId attachId,
        String url,
        FileId fileId,
        String title,
        String text,
        PostStatus postStatus,
        PostVisibility postVisibility
) {}
