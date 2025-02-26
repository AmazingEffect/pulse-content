package com.pulse.content.adapter.out.persistence.adapter;


import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.repository.PostRepository;
import com.pulse.content.application.port.out.CreateContentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Post;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreateContentPersistAdapter implements CreateContentPort {
    private final PostRepository postRepository;
    private final ContentMapper contentMapper;


    @Override
    public Post create(Post post) {
        PostEntity postEntity = contentMapper.domainToEntity(post);

        PostEntity cratedPostEntity = postRepository.save(postEntity);

        return contentMapper.entityToDomain(cratedPostEntity);
    }
}
