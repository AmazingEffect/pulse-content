package com.pulse.content.adapter.in.web.dto.response;


import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.key.ContentId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindContentResponseDTO {
    private ContentId contentId;
    private List<String> hashTags;
    private String title;
    private String text;
}
