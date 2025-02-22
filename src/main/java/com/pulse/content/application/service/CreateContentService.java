package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.application.port.in.outbox.CreateContentsUseCase;
import com.pulse.content.application.port.out.CreateContentPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.Post;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateContentService implements CreateContentsUseCase {
    private final CreateContentPort createContentPort;
    private final ContentMapper contentMapper;

    @Override
    @Transactional
    public CreateContentResponseDTO create(CreateContentRequestDTO createContentRequestDto) {
        Post post = contentMapper.dtoToDomain(createContentRequestDto);
        // todo: 1. hashTag 저장

        // todo: 2. 게시글 상태(postStatus) 세팅

        // todo: 3. dto -> domain 변환

        // todo: 4. 게시글 저장
//        Post post = contentMapper.createRequestDtoToDomain(createContentRequestDto);
        Post createdPost = createContentPort.create(post);

        // todo: 5. 첨부 파일 저장(이벤트)
        return contentMapper.domainToCreateResponseDTO(createdPost);
    }
}
