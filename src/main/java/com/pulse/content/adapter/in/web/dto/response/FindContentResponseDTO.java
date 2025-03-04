package com.pulse.content.adapter.in.web.dto.response;


import com.pulse.content.domain.key.ContentId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindContentResponseDTO {
    private ContentId contentId;
    private String title;
    private String text;
}
