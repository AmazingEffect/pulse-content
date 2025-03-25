package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.PostHashTagMapEntity;
import com.pulse.content.domain.map.PostHashTag;
import org.mapstruct.*;
import org.springframework.util.ObjectUtils;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostHashTagMapper {

    @Mapping(target = "postHashTagId", source = "postHashTagId.id")
    PostHashTagMapEntity domainToEntity(PostHashTag postHashTag);


    @AfterMapping
    default void mapDomainToEntity(PostHashTag domain, @MappingTarget PostHashTagMapEntity.PostHashTagMapEntityBuilder builder) {
        if (!ObjectUtils.isEmpty(domain.getPost())) {
            builder.postEntity(ContentMapper.INSTANCE.domainToEntity(domain.getPost()));
        }
        if (!ObjectUtils.isEmpty(domain.getHashTag())) {
            // hashtag 변환
        }
    }

    @Mapping(target = "postHashTagId.id", source = "postHashTagId")
    PostHashTag entityToDomain(PostHashTagMapEntity postHashTagMapEntity);


    @AfterMapping
    default void mapEntityToDomain(PostHashTagMapEntity entity, @MappingTarget PostHashTag.PostHashTagBuilder builder) {
        if (!ObjectUtils.isEmpty(entity.getPostEntity())) {
            builder.post(ContentMapper.INSTANCE.entityToDomain(entity.getPostEntity()));
        }
        if (!ObjectUtils.isEmpty(entity.getHashTagEntity())) {
            // hashtag 변환
        }
    }
}
