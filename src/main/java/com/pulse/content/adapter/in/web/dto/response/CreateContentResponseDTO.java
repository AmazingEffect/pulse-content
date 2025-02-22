package com.pulse.content.adapter.in.web.dto.response;

import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.common.enumerate.PostVisibility;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.PostId;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContentResponseDTO {
    private PostId postId;
    private MemberId memberId;
    private List<Long> hashTagIds;
    private List<Long> categoryIds;
    private AttachId attachId;
    private String url;
    private FileId fileId;
    private String title;
    private String text;
    private PostStatus postStatus;
    private PostVisibility postVisibility;
    private HashTagResponse hashTagResponse;

    public static class HashTagResponse {
        private Long hashTagId;
        private String name;
    }

}
