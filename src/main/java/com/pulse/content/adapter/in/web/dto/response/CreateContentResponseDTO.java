package com.pulse.content.adapter.in.web.dto.response;

import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.ContentId;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateContentResponseDTO {

    private ContentId contentId;
    private MemberId memberId;
    private List<Long> hashTagIds;
    private AttachId attachId;
    private String url;
    private FileId fileId;
    private String title;
    private String text;
    private ContentStatus contentStatus;
    private ContentVisibility contentVisibility;
    private HashTagResponse hashTagResponse;

    public static class HashTagResponse {
        private Long hashTagId;
        private String name;
    }

}
