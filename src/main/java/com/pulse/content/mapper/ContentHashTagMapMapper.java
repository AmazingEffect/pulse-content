package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.ContentHashTagMapEntity;
import com.pulse.content.domain.map.ContentHashTagMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ContentHashTagMapMapper {

    @Mapping(target = "contentHashTagMapId", source = "contentHashTagMapId.id")
    ContentHashTagMapEntity domainToEntity(ContentHashTagMap contentHashTagMap);

    @Mapping(target = "contentHashTagMapId.id", source = "contentHashTagMapId")
    ContentHashTagMap entityToDomain(ContentHashTagMapEntity contentHashTagMapEntity);
}
