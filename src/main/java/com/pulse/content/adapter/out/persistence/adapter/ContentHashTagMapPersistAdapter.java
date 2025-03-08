package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.ContentHashTagMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.ContentHashTagMapRepository;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.application.port.out.map.DeleteContentHashTagMapPort;
import com.pulse.content.application.port.out.map.FindContentHashTagMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.mapper.ContentHashTagMapMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ContentHashTagMapPersistAdapter implements CreateContentHashTagMapPort, FindContentHashTagMapPort, DeleteContentHashTagMapPort {

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

    /**
     * ContentHashTag 목록 저장
     * @param contentHashTagMaps - 저장할 ContentHashTag 목록
     * @return 저장된 contentHashTagMaps 목록
     */
    @Override
    public List<ContentHashTagMap> createAll(List<ContentHashTagMap> contentHashTagMaps) {
        List<ContentHashTagMapEntity> contentHashTagMapEntities = contentHashTagMaps.stream()
                .map(contentHashTagMapMapper::domainToEntity)
                .toList();

        List<ContentHashTagMapEntity> createdContentHashTagMapEntities = contentHashTagMapRepository.saveAll(contentHashTagMapEntities);

        return createdContentHashTagMapEntities.stream()
                .map(contentHashTagMapMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    /**
     * ContentId로 ContentHashTagMap 조회
     * @param contentId - ContentHashTagMap 을 조회할 ContentId
     * @return 조회된 ContentHashTagMap
     */
    @Override
    public List<ContentHashTagMap> findByContentId(Long contentId) {
        List<ContentHashTagMapEntity> contentHashTagMapEntities = contentHashTagMapRepository.findAllByContentEntity_ContentId(contentId);

        return contentHashTagMapEntities.stream()
                .map(contentHashTagMapMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    /**
     * ContentHashTagMapId 리스트에 해당하는 ContentHashTagMap 전부 삭제
     * @param contentHashTagMapIds - 삭제할 ContentHashTagMap 리스트
     */
    @Override
    public void deleteAll(List<Long> contentHashTagMapIds) {
        contentHashTagMapRepository.deleteAllById(contentHashTagMapIds);
    }
}
