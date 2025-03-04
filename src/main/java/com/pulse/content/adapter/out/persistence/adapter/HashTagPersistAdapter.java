package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import com.pulse.content.adapter.out.persistence.repository.HashTagRepository;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.HashTag.FindHashTagPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.HashTag;
import com.pulse.content.mapper.HashTagMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class HashTagPersistAdapter implements CreateHashTagPort, FindHashTagPort {

    private final HashTagRepository hashTagRepository;
    private final HashTagMapper hashTagMapper;

    /**
     * HashTag 저장 메서드
     * @param hashTag - 저장할 HashTag 데이터
     * @return 저장된 HashTag
     */
    @Override
    public HashTag create(HashTag hashTag) {
        HashTagEntity hashTagEntity = hashTagMapper.domainToEntity(hashTag);
        HashTagEntity createdHashTagEntity = hashTagRepository.save(hashTagEntity);

        return hashTagMapper.entityToDomain(createdHashTagEntity);
    }

    /**
     * HashTag 리스트 저장
     * @param hashTags - 저장할 HashTag 데이터 목록
     * @return 저장된 HashTag 목록
     */
    @Override
    public List<HashTag> createAll(List<HashTag> hashTags) {
        List<HashTagEntity> hashTagEntities = hashTags.stream()
                .map(hashTagMapper::domainToEntity)
                .toList();

        List<HashTagEntity> createdHashTagEntities = hashTagRepository.saveAll(hashTagEntities);

        return createdHashTagEntities.stream()
                .map(hashTagMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    /**
     * 해시태그명으로 해시태그 조회
     * @param name - 조회할 해시태그명
     * @return 조회된 해시태그
     */
    @Override
    public HashTag findByName(String name) {
        Optional<HashTagEntity> hashTagEntity = hashTagRepository.findByName(name);
        return hashTagEntity.map(hashTagMapper::entityToDomain).orElse(null);
    }

    /**
     * 해시태그명 목록에 일치하는 해시태그 목록 조회
     * @param names - 조회할 해시태그명 목록
     * @return 조회된 해시태그 목록
     */
    @Override
    public List<HashTag> findByNames(List<String> names) {
        List<HashTagEntity> hashTagEntities = hashTagRepository.findByNameIn(names);

        return hashTagEntities.stream()
                .map(hashTagMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
