package com.pulse.content.adapter.in.web.dto.response;


import com.pulse.content.domain.key.PostId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindContentResponseDTO {
    private PostId postId;
    private String title;
    private String text;
}
