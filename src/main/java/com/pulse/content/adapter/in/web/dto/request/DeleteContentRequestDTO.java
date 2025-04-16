package com.pulse.content.adapter.in.web.dto.request;

import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.key.MemberId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteContentRequestDTO {

    @Valid
    @NotNull
    private MemberId deleterId;     // 콘텐츠 삭제 요청자 id
    @Valid
    @NotNull
    private ContentId contentId;    // 삭제할 콘텐츠 Id
}
