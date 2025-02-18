package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.application.port.in.outbox.CreateContentsUseCase;
import com.pulse.content.application.port.out.CreateContentPort;
import com.pulse.content.domain.Post;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateContentService implements CreateContentsUseCase {
    private final CreateContentPort createContentPort;
    private final ContentMapper contentMapper;

    @Override
    public CreateContentResponseDTO create(CreateContentRequestDTO createContentRequestDto) {
        Post post = contentMapper.createRequestDtoToDomain(createContentRequestDto);
        Post savePost = createContentPort.create(post);

        CreateContentResponseDTO createContentResponseDTO = contentMapper.domainToCreateResponseDTO(savePost);

        return createContentResponseDTO;
    }
}
