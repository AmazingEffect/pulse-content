package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.response.ContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.repository.PostRepository;
import com.pulse.content.application.port.FindContentPort;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.mapper.ContentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FindContentService {

    private final FindContentPort findContentPort;
    private final ContentMapper contentMapper;

    public ContentResponseDTO findContent(PostId postId) {
        Post post = findContentPort.findContent(postId);
        return contentMapper.domainToResponseDTO(post);
    }
}
