package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import com.pulse.content.domain.HashTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HashTagMapper {

    @Mapping(target = "hasTagId", source = "hasTagId.id")
    HashTagEntity domainToEntity(HashTag hashTag);

    @Mapping(target = "hasTagId.id", source = "hasTagId")
    HashTag entityToDomain(HashTagEntity hashTagEntity);
}
