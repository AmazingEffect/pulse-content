package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.vo.PostAttachmentEntity;
import com.pulse.content.domain.vo.PostAttachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostAttachmentMapper {

    PostAttachmentMapper INSTANCE = Mappers.getMapper(PostAttachmentMapper.class);

    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "fileId", source = "fileId.id")
    PostAttachmentEntity domainToEntity(PostAttachment postAttachment);

    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "fileId.id", source = "fileId")
    PostAttachment entityToDomain(PostAttachmentEntity postAttachmentEntity);
}
