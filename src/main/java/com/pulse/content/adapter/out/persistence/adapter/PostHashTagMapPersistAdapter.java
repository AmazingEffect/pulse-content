package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.PostHashTagMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.PostHashTagMapRepository;
import com.pulse.content.application.port.out.map.CreatePostHashTagMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.PostHashTagMap;
import com.pulse.content.mapper.PostHashTagMapMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostHashTagMapPersistAdapter implements CreatePostHashTagMapPort {

    private final PostHashTagMapRepository postHashTagMapRepository;
    private final PostHashTagMapMapper postHashTagMapMapper;

    /**
     * PostHashTag 저장 메서드
     * @param postHashTagMap - 저장할 PostHashTagMap 도메인
     * @return 저장된 PostHashTagMap
     */
    @Override
    public PostHashTagMap create(PostHashTagMap postHashTagMap) {
        PostHashTagMapEntity postHashTagMapEntity = postHashTagMapMapper.domainToEntity(postHashTagMap);
        PostHashTagMapEntity createdPostHashTagMapEntity = postHashTagMapRepository.save(postHashTagMapEntity);

        return postHashTagMapMapper.entityToDomain(createdPostHashTagMapEntity);
    }
}
