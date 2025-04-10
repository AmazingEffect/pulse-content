package com.pulse.content.adapter.in.web.dto.response;

import com.pulse.content.domain.key.ContentId;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteContentResponseDTO {

    private ContentId contentId;
}
