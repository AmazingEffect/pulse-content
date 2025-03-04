package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import com.pulse.content.adapter.out.persistence.repository.HashTagRepository;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.HashTag;
import com.pulse.content.mapper.HashTagMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class HashTagPersistAdapter implements CreateHashTagPort {

    private final HashTagRepository hashTagRepository;
    private final HashTagMapper hashTagMapper;

    /**
     * HashTag 저장 메서드
     * @param hashTag - 저장할 HashTag 데이터
     * @return 저장한 HashTag
     */
    @Override
    public HashTag create(HashTag hashTag) {
        HashTagEntity hashTagEntity = hashTagMapper.domainToEntity(hashTag);
        HashTagEntity createdHashTagEntity = hashTagRepository.save(hashTagEntity);

        return hashTagMapper.entityToDomain(createdHashTagEntity);
    }
}
