package com.pulse.content.adapter.out.persistence.adapter;


import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.adapter.out.persistence.entity.map.ContentHashTagMapEntity;
import com.pulse.content.adapter.out.persistence.repository.ContentRepository;
import com.pulse.content.adapter.out.persistence.repository.map.ContentHashTagMapRepository;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.DeleteContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.application.port.out.content.UpdateContentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.mapper.ContentHashTagMapMapper;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentPersistAdapter implements CreateContentPort, FindContentPort, UpdateContentPort, DeleteContentPort {

    private final ContentHashTagMapRepository contentHashTagMapRepository;
    private final ContentRepository contentRepository;

    private final ContentHashTagMapMapper contentHashTagMapMapper;
    private final ContentMapper contentMapper;

    /**
     * 게시글 저장
     * @param content - 저장할 게시글 정보
     * @return 저장된 게시글
     */
    @Override
    public Content create(Content content) {
        ContentEntity contentEntity = contentMapper.domainToEntity(content);

        ContentEntity cratedContentEntity = contentRepository.save(contentEntity);

        return contentMapper.entityToDomain(cratedContentEntity);
    }

    @Override
    public Content findContent(ContentId contentId) {
        Optional<ContentEntity> contentEntity = contentRepository.findById(contentId.id());
        List<ContentHashTagMapEntity> contentHashTagMapEntities = contentHashTagMapRepository.findAllByContentEntity_ContentId(contentId.id());

        // contentHashTagMap 도메인으로 변환
        List<ContentHashTagMap> contentHashTagMap = contentHashTagMapEntities.stream()
                .map(contentHashTagMapMapper::entityToDomain)
                .collect(Collectors.toList());

        // 해시태그명만 추출
        List<String> hashTags = contentHashTagMap.stream()
                .map(hashtagMap -> hashtagMap.getHashTag().getName())
                .collect(Collectors.toList());

        // content에 hashtag를 넣어주기 위해 custom mapper method 호출
        return contentEntity
                .map(entity -> contentMapper.entityToDomain(entity, hashTags, contentHashTagMap))
                .orElse(null);
    }

    /**
     * 콘텐츠 수정
     * @param content - 수정할 콘텐츠
     * @return - 수정된 콘텐츠
     */
    @Override
    public Content update(Content content) {
        ContentEntity contentEntity = contentMapper.domainToEntity(content);
        ContentEntity updatedContentEntity = contentRepository.save(contentEntity);

        return contentMapper.entityToDomain(updatedContentEntity);
    }

    /**
     * 콘텐츠 삭제
     * @param id 아이디
     */
    @Override
    public void deleteById(Long id) {
        contentRepository.deleteById(id);
    }
}
