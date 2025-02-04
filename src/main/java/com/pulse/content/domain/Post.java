package com.pulse.content.domain;

import com.pulse.content.adapter.out.persistence.entity.PostStatus;
import com.pulse.content.adapter.out.persistence.entity.PostVisibility;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}

