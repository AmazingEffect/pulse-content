package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.ContentCategoryMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.ContentCategoryRepository;
import com.pulse.content.application.port.out.map.CreateContentCategoryMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.mapper.ContentCategoryMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ContentCategoryPersistMapAdapter implements CreateContentCategoryMapPort {

    private final ContentCategoryRepository contentCategoryRepository;
    private final ContentCategoryMapper contentCategoryMapper;


    /**
     * ContentCategoryMap 저장
     * @param contentCategoryMap - ContentCategoryMap 객체
     * @return - 저장된 ContentCategoryMap
     */
    @Override
    public ContentCategoryMap create(ContentCategoryMap contentCategoryMap) {
        ContentCategoryMapEntity contentCategoryMapEntity = contentCategoryMapper.domainToEntity(contentCategoryMap);
        ContentCategoryMapEntity createdContentCategoryMapEntity = contentCategoryRepository.save(contentCategoryMapEntity);

        return contentCategoryMapper.entityToDomain(createdContentCategoryMapEntity);
    }
}
