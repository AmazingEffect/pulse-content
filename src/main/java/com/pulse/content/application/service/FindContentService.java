package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.application.port.FindContentPort;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.mapper.ContentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindContentService implements FindContentUseCase {

    private final FindContentPort findContentPort;
    private final ContentMapper contentMapper;

    @Override
    public FindContentResponseDTO findContent(PostId postId) {
        Post findPost = findContentPort.findContent(postId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findPost);
    }
}
