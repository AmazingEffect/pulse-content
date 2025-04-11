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
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentPersistAdapter implements CreateContentPort, FindContentPort, UpdateContentPort, DeleteContentPort {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final ContentHashTagMapRepository contentHashTagMapRepository;
    private final ContentHashTagMapMapper contentHashTagMapMapper;

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

    /**
     * 콘텐츠 조회
     * @param contentId 조회할 콘텐츠 id
     * @return 조회한 콘텐츠
     */
    @Override
    public Content findContent(ContentId contentId) {
        Optional<ContentEntity> contentEntity = contentRepository.findById(contentId.id());
        Content content = contentEntity.map(contentMapper::entityToDomain).orElse(null);

        if (!ObjectUtils.isEmpty(content)) {
            List<ContentHashTagMapEntity> contentHashTagMapEntities = contentHashTagMapRepository.findAllByContentEntity_ContentId(contentId.id());
            List<ContentHashTagMap> contentHashTagMaps = contentHashTagMapEntities.stream().map(contentHashTagMapMapper::entityToDomain).toList();
            content.putContentHasTagMap(contentHashTagMaps);
        }

        return content;
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
