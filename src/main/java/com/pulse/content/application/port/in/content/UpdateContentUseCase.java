package com.pulse.content.application.port.in.content;

import com.pulse.content.adapter.in.web.dto.request.UpdateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;

public interface UpdateContentUseCase {
    UpdateContentResponseDTO update(UpdateContentRequestDTO updateContentRequestDTO);
}
