package com.pulse.content.adapter.in.web.dto.request;

import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.key.MemberId;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteContentRequestDTO {

    private MemberId memberId;
    private ContentId contentId;
}
