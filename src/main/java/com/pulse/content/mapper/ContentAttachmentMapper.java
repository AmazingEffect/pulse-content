package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import com.pulse.content.domain.vo.ContentAttachment;
import com.pulse.content.mapper.helper.ContentMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ContentMapperHelper.class}
)
public interface ContentAttachmentMapper {

    @Mapping(target = "contentAttachmentId", source = "contentAttachmentId.id")
    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "fileId", source = "fileId.id")
    @Mapping(target = "contentEntity", source = "content", qualifiedByName = "contentDomainToEntity")
    ContentAttachmentEntity domainToEntity(ContentAttachment contentAttachment);

    @Mapping(target = "contentAttachmentId.id", source = "contentAttachmentId")
    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "fileId.id", source = "fileId")
    @Mapping(target = "content", source = "contentEntity", qualifiedByName = "contentEntityToDomain")
    ContentAttachment entityToDomain(ContentAttachmentEntity contentAttachmentEntity);
}
