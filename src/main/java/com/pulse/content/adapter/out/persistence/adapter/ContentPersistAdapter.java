package com.pulse.content.adapter.out.persistence.adapter;


import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.adapter.out.persistence.repository.ContentRepository;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentPersistAdapter implements CreateContentPort, FindContentPort {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    /**
     * 게시글 저장
     * @param content - 저장할 게시글 정보
     * @return 저장된 게시글
     */
    @Override
    public Content create(Content content) {
        ContentEntity contentEntity = contentMapper.domainToEntity(content);

        ContentEntity cratedContentEntity = contentRepository.save(contentEntity);

        return contentMapper.entityToDomain(cratedContentEntity);
    }

    @Override
    public Content findContent(ContentId contentId) {
        Optional<ContentEntity> contentEntity = contentRepository.findById(contentId.id());
        return contentEntity.map(contentMapper::entityToDomain).orElse(null);
    }
}
