package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentDetailEntity;
import com.pulse.content.domain.vo.ContentDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentDetailMapper {

    ContentDetailMapper INSTANCE = Mappers.getMapper(ContentDetailMapper.class);

    ContentDetailEntity domainToEntity(ContentDetail contentDetail);

    ContentDetail entityToDomain(ContentDetailEntity contentDetailEntity);
}
