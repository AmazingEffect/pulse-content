package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.ContentCategoryMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.ContentCategoryRepository;
import com.pulse.content.application.port.out.map.CreateContentCategoryMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.mapper.ContentCategoryMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ContentCategoryPersistMapAdapter implements CreateContentCategoryMapPort {

    private final ContentCategoryRepository contentCategoryRepository;
    private final ContentCategoryMapper contentCategoryMapper;


    /**
     * ContentCategoryMap 저장
     * @param contentCategoryMap - 저장할 ContentCategoryMap
     * @return - 저장된 ContentCategoryMap
     */
    @Override
    public ContentCategoryMap create(ContentCategoryMap contentCategoryMap) {
        ContentCategoryMapEntity contentCategoryMapEntity = contentCategoryMapper.domainToEntity(contentCategoryMap);
        ContentCategoryMapEntity createdContentCategoryMapEntity = contentCategoryRepository.save(contentCategoryMapEntity);

        return contentCategoryMapper.entityToDomain(createdContentCategoryMapEntity);
    }

    /**
     * ContentCategoryMap 리스트 저장
     * @param contentCategoryMaps - 저장할 ContentCategoryMap 리스트
     * @return 저장된 ContentCategoryMap 리스트
     */
    @Override
    public List<ContentCategoryMap> createAll(List<ContentCategoryMap> contentCategoryMaps) {
        List<ContentCategoryMapEntity> contentCategoryMapEntities = contentCategoryMaps.stream()
                .map(contentCategoryMapper::domainToEntity)
                .toList();

        List<ContentCategoryMapEntity> createdContentCategoryMapEntities = contentCategoryRepository.saveAll(contentCategoryMapEntities);

        return createdContentCategoryMapEntities.stream()
                .map(contentCategoryMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
