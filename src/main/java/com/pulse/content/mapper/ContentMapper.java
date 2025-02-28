package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.domain.Post;
import com.pulse.content.mapper.helper.ContentMapperHelper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
/**
 * componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다. (외부에서 주입받아서 사용하면 된다.)
 * unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ContentMapperHelper.class}
)
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mapping(target = "postId", source = "postId.id")
    @Mapping(target = "memberId", source = "memberId.id")
    @Mapping(target = "postAttachmentEntities", source = "postAttachments", qualifiedByName = "postAttachmentsDomainToEntity")
    @Mapping(target = "title", source = "contentDetail.title")
    @Mapping(target = "text", source = "contentDetail.text")
    PostEntity domainToEntity(Post post);

    @Mapping(target = "postId.id", source = "postId")
    @Mapping(target = "memberId.id", source = "memberId")
    @Mapping(target = "postAttachments", source = "postAttachmentEntities", qualifiedByName = "postAttachmentsEntityToDomain")
    @Mapping(target = "contentDetail", expression = "java(contentMapperHelper.contentDetailEntityToDomain(entity.getTitle(), entity.getText()))")
    Post entityToDomain(PostEntity entity);

    CreateContentResponseDTO domainToCreateResponseDTO(Post savePost);

    @IterableMapping(elementTargetType = Post.class)
    Post dtoToDomain(CreateContentRequestDTO createContentRequestDto);

    // 응답 도메인을 회원가입 응답 DTO로 변환
    FindContentResponseDTO domainToResponseDTO(Post post);

}
