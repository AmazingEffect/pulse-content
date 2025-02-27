package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.domain.Post;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

/**
 * componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다. (외부에서 주입받아서 사용하면 된다.)
 * unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mapping(target = "postId", source = "postId.id")
    @Mapping(target = "memberId", source = "memberId.id")
    PostEntity domainToEntity(Post post);

    @AfterMapping
    default void domainToEntity(Post domain, @MappingTarget PostEntity.PostEntityBuilder builder) {
        if (!ObjectUtils.isEmpty(domain.getPostAttachments())) {
            builder.postAttachmentEntities(
                    domain.getPostAttachments().stream()
                            .map(PostAttachmentMapper.INSTANCE::domainToEntity)
                            .collect(Collectors.toList())
            );
        }

        if (!ObjectUtils.isEmpty(domain.getContentDetail())) {
            builder.contentDetailEntity(ContentDetailMapper.INSTANCE.domainToEntity(domain.getContentDetail()));
        }
    }

    @Mapping(target = "postId.id", source = "postId")
    @Mapping(target = "memberId.id", source = "memberId")
    Post entityToDomain(PostEntity savePostEntity);

    @AfterMapping
    default void entityToDomain(PostEntity entity, @MappingTarget Post.PostBuilder builder) {
        if (!ObjectUtils.isEmpty(entity.getPostAttachmentEntities())) {
            builder.postAttachments(
                    entity.getPostAttachmentEntities().stream()
                            .map(PostAttachmentMapper.INSTANCE::entityToDomain)
                            .collect(Collectors.toList())
            );
        }

        if (!ObjectUtils.isEmpty(entity.getContentDetailEntity())) {
            builder.contentDetail(ContentDetailMapper.INSTANCE.entityToDomain(entity.getContentDetailEntity()));
        }
    }

    CreateContentResponseDTO domainToCreateResponseDTO(Post savePost);

    @IterableMapping(elementTargetType = Post.class)
    Post dtoToDomain(CreateContentRequestDTO createContentRequestDto);

    // 응답 도메인을 회원가입 응답 DTO로 변환
    FindContentResponseDTO domainToResponseDTO(Post post);

}
