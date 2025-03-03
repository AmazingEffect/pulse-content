package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.PostCategoryRepository;
import com.pulse.content.application.port.out.map.CreatePostCategoryMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.mapper.PostCategoryMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostCategoryPersistMapAdapter implements CreatePostCategoryMapPort {

    private final PostCategoryRepository postCategoryRepository;
    private final PostCategoryMapper postCategoryMapper;


    /**
     * PostCategoryMap 저장
     * @param postCategoryMap - PostCategoryMap 객체
     * @return - 저장된 PostCategoryMap
     */
    @Override
    public PostCategoryMap create(PostCategoryMap postCategoryMap) {
        PostCategoryMapEntity postCategoryMapEntity = postCategoryMapper.domainToEntity(postCategoryMap);
        PostCategoryMapEntity createdPostCategoryMapEntity = postCategoryRepository.save(postCategoryMapEntity);

        return postCategoryMapper.entityToDomain(createdPostCategoryMapEntity);
    }
}
