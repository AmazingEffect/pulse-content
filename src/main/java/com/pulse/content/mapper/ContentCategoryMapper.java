package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.ContentCategoryMapEntity;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.mapper.helper.ContentCategoryMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ContentCategoryMapperHelper.class}
)
public interface ContentCategoryMapper {

    @Mapping(target = "contentCategoryMapId", source = "contentCategoryMapId.id")
    @Mapping(target = "contentEntity", source = "content", qualifiedByName = "contentDomainToEntity")
    @Mapping(target = "categoryEntity", source = "category", qualifiedByName = "categoryDomainToEntity")
    ContentCategoryMapEntity domainToEntity(ContentCategoryMap contentCategoryMap);


    @Mapping(target = "contentCategoryMapId.id", source = "contentCategoryMapId")
    @Mapping(target = "content", source = "contentEntity", qualifiedByName = "contentEntityToDomain")
    @Mapping(target = "category", source = "categoryEntity", qualifiedByName = "categoryEntityToDomain")
    ContentCategoryMap entityToDomain(ContentCategoryMapEntity contentCategoryMapEntity);
}
