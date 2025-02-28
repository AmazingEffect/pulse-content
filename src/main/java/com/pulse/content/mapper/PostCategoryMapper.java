package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.domain.Category;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.map.PostCategoryMap;
import org.mapstruct.*;
import org.springframework.util.ObjectUtils;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCategoryMapper {

    @Mapping(target = "postCategoryMapId", source = "postCategoryMapId.id")
    PostCategoryMapEntity domainToEntity(PostCategoryMap postCategoryMap);

    @AfterMapping
    default void mapDomainToEntity(PostCategoryMap domain, @MappingTarget PostCategoryMapEntity.PostCategoryMapEntityBuilder builder) {
        if (!ObjectUtils.isEmpty(domain.getPost())) {
            builder.postEntity(ContentMapper.INSTANCE.domainToEntity(domain.getPost()));
        }
        if (!ObjectUtils.isEmpty(domain.getCategory())) {
            // category 변환
        }
    }

    @Mapping(target = "postCategoryMapId.id", source = "postCategoryMapId")
    PostCategoryMap entityToDomain(PostCategoryMapEntity postCategoryEntity);

    @AfterMapping
    default void mapEntityToDomain(PostCategoryMapEntity entity, @MappingTarget PostCategoryMap.PostCategoryMapBuilder builder) {
        if (!ObjectUtils.isEmpty(entity.getPostEntity())) {
            builder.post(ContentMapper.INSTANCE.entityToDomain(entity.getPostEntity()));
        }
        if (!ObjectUtils.isEmpty(entity.getCategoryEntity())) {
            // category 변환
        }
    }
}
