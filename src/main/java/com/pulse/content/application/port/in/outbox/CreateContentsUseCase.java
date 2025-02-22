package com.pulse.content.application.port.in.outbox;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;

public interface CreateContentsUseCase {
    CreateContentResponseDTO create(CreateContentRequestDTO post);
}
