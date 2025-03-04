package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.ContentHashTagMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.ContentHashTagMapRepository;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.mapper.ContentHashTagMapMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ContentHashTagMapPersistAdapter implements CreateContentHashTagMapPort {

    private final ContentHashTagMapRepository contentHashTagMapRepository;
    private final ContentHashTagMapMapper contentHashTagMapMapper;

    /**
     * ContentHashTag 저장 메서드
     * @param contentHashTagMap - 저장할 ContentHashTag 도메인
     * @return 저장된 ContentHashTag
     */
    @Override
    public ContentHashTagMap create(ContentHashTagMap contentHashTagMap) {
        ContentHashTagMapEntity contentHashTagMapEntity = contentHashTagMapMapper.domainToEntity(contentHashTagMap);
        ContentHashTagMapEntity createdContentHashTagMapEntity = contentHashTagMapRepository.save(contentHashTagMapEntity);

        return contentHashTagMapMapper.entityToDomain(createdContentHashTagMapEntity);
    }
}
