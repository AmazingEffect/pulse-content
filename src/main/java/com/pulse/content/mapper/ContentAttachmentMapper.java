package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import com.pulse.content.domain.vo.ContentAttachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentAttachmentMapper {

    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "fileId", source = "fileId.id")
    ContentAttachmentEntity domainToEntity(ContentAttachment contentAttachment);

    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "fileId.id", source = "fileId")
    ContentAttachment entityToDomain(ContentAttachmentEntity contentAttachmentEntity);
}
