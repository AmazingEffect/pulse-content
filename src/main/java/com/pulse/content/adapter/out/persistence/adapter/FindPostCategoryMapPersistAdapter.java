package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.adapter.out.persistence.repository.map.PostCategoryMapRepository;
import com.pulse.content.application.port.FindPostCategoryMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.mapper.PostCategoryMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindPostCategoryMapPersistAdapter implements FindPostCategoryMapPort {
    private final PostCategoryMapRepository postCategoryMapRepository;
    private final PostCategoryMapper postCategoryMapper;

    @Override
    public List<PostCategoryMap> findCategoriesByPostId(PostId postId) {
        return postCategoryMapRepository.findByPostEntity_PostId(postId)
                .stream()
                .map(postCategoryMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
