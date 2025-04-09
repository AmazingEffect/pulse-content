package com.pulse.content.application.port.in.content;

import com.pulse.content.adapter.in.web.dto.request.DeleteContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.DeleteContentResponseDTO;

public interface DeleteContentUseCase {
    DeleteContentResponseDTO delete(DeleteContentRequestDTO deleteContentRequestDTO);
}
