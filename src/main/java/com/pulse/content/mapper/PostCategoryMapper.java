package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.domain.Category;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.map.PostCategoryMap;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCategoryMapper {

    @Mapping(target = "postCategoryMapId", source = "postCategoryMapId.id")
    PostCategoryMapEntity domainToEntity(PostCategoryMap postCategoryMap);

    @AfterMapping
    default void mapDomainToEntity(PostCategoryMap domain, @MappingTarget PostCategoryMapEntity.PostCategoryMapEntityBuilder builder) {
        if (domain.getPost() != null) {
            builder.postEntity(ContentMapper.INSTANCE.domainToEntity(domain.getPost()));
        }
        if (domain.getCategory() != null) {
            // category 변환
        }
    }

    @Mapping(target = "postCategoryMapId.id", source = "postCategoryMapId")
    PostCategoryMap entityToDomain(PostCategoryMapEntity postCategoryEntity);

    @AfterMapping
    default void mapEntityToDomain(PostCategoryMapEntity entity, @MappingTarget PostCategoryMap.PostCategoryMapBuilder builder) {
        if (entity.getPostEntity() != null) {
            builder.post(ContentMapper.INSTANCE.entityToDomain(entity.getPostEntity()));
        }
        if (entity.getCategoryEntity() != null) {
            // category 변환
        }
    }
}
