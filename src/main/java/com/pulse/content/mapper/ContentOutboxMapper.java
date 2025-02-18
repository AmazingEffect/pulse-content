package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.ContentOutboxEntity;
import com.pulse.content.domain.ContentOutbox;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentOutboxMapper {
    ContentOutboxEntity domainToEntity(ContentOutbox outbox);

    ContentOutbox entityToDomain(ContentOutboxEntity entity);
}
