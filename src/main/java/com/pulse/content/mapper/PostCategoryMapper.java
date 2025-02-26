package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.domain.map.PostCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCategoryMapper {
    @Mapping(target = "id", source = "id.id")
    @Mapping(target = "postId", source = "postId.id")
    @Mapping(target = "categoryId")
    PostCategoryMapEntity domainToEntity(PostCategory postCategory);

    @Mapping(target = "id.id", source = "id")
    @Mapping(target = "postId.id", source = "postId")
    PostCategory entityToDomain(PostCategoryMapEntity postCategoryEntity);
}
