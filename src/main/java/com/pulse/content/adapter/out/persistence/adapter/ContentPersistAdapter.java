package com.pulse.content.adapter.out.persistence.adapter;


import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.repository.PostRepository;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentPersistAdapter implements CreateContentPort, FindContentPort {
    private final PostRepository postRepository;
    private final ContentMapper contentMapper;

    /**
     * 게시글 저장
     * @param post - 저장할 게시글 정보
     * @return 저장된 게시글
     */
    @Override
    public Post create(Post post) {
        PostEntity postEntity = contentMapper.domainToEntity(post);

        PostEntity cratedPostEntity = postRepository.save(postEntity);

        return contentMapper.entityToDomain(cratedPostEntity);
    }

    @Override
    public Post findContent(PostId postId) {
        PostEntity postEntity = postRepository.findById(postId.id())
                .orElseThrow(() -> new IllegalArgumentException("Content not found"));
        return contentMapper.entityToDomain(postEntity);
    }

}
