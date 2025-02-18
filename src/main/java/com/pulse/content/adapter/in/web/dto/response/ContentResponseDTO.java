package com.pulse.content.adapter.in.web.dto.response;


import com.pulse.content.domain.key.PostId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponseDTO {
    private PostId postId;
    private String title;
    private String text;
}
