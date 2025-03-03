package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.mapper.helper.PostCategoryMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PostCategoryMapperHelper.class}
)
public interface PostCategoryMapper {

    @Mapping(target = "postCategoryMapId", source = "postCategoryMapId.id")
    @Mapping(target = "postEntity", source = "post", qualifiedByName = "postDomainToEntity")
    @Mapping(target = "categoryEntity", source = "category", qualifiedByName = "categoryDomainToEntity")
    PostCategoryMapEntity domainToEntity(PostCategoryMap postCategoryMap);


    @Mapping(target = "postCategoryMapId.id", source = "postCategoryMapId")
    @Mapping(target = "post", source = "postEntity", qualifiedByName = "postEntityToDomain")
    @Mapping(target = "category", source = "categoryEntity", qualifiedByName = "categoryEntityToDomain")
    PostCategoryMap entityToDomain(PostCategoryMapEntity postCategoryEntity);
}
