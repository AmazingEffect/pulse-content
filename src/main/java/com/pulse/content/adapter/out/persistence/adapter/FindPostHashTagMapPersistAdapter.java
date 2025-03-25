package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.repository.map.PostHashTagMapRepository;
import com.pulse.content.application.port.FindPostHashTagMapPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostHashTag;
import com.pulse.content.mapper.PostHashTagMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindPostHashTagMapPersistAdapter implements FindPostHashTagMapPort {

    private final PostHashTagMapRepository postHashTagMapRepository;
    private final PostHashTagMapper postHashTagMapper;

    @Override
    public List<PostHashTag> findHashTagsByPostId(PostId postId) {
        return postHashTagMapRepository.findByPostEntity_PostId(postId)
                .stream()
                .map(postHashTagMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
