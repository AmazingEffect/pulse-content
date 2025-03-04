package com.pulse.content.application.port.in.content;

import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.domain.key.ContentId;

public interface FindContentUseCase {
    FindContentResponseDTO findContent(ContentId contentId);
}
