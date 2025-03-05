package com.pulse.content.mapper.helper;

import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.domain.Content;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentMapperHelper {

    private final ContentMapper contentMapper;


    /**
     * 콘텐츠 도메인 객체를 콘텐츠 엔티티 객체로 변환
     * @param content - 콘텐츠 도메인
     * @return 콘텐츠 엔티티
     */
    @Named("contentDomainToEntity")
    public ContentEntity contentDomainToEntity(Content content) {
        return contentMapper.domainToEntity(content);
    }
    /**
     * 콘텐츠 엔티티 객체를 콘텐츠 도메인 객체로 변환
     * @param contentEntity - 콘텐츠 엔티티
     * @return 콘텐츠 도메인
     */
    @Named("contentEntityToDomain")
    public Content contentEntityToDomain(ContentEntity contentEntity) {
        return contentMapper.entityToDomain(contentEntity);
    }
}
