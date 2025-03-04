package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.map.PostHashTagMapEntity;
import com.pulse.content.domain.map.PostHashTagMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PostHashTagMapMapper {

    @Mapping(target = "postHashTagMapId", source = "postHashTagMapId.id")
    PostHashTagMapEntity domainToEntity(PostHashTagMap postHashTagMap);

    @Mapping(target = "postHashTagMapId.id", source = "postHashTagMapId")
    PostHashTagMap entityToDomain(PostHashTagMapEntity postHashTagMapEntity);
}
