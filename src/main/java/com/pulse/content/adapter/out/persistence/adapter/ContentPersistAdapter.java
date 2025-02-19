package com.pulse.content.adapter.out.persistence.adapter;


import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.repository.PostRepository;
import com.pulse.content.application.port.FindContentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentPersistAdapter implements FindContentPort {
    private final PostRepository postRepository;
    private final ContentMapper contentMapper;

    @Override
    public Post findContent(PostId postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Content not found"));
        return contentMapper.entityToDomain(postEntity);
    }
}
