package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.domain.Post;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다. (외부에서 주입받아서 사용하면 된다.)
 * unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentMapper {

    @Mapping(target = "postId", source = "postId.id")
    @Mapping(target = "memberId", source = "memberId.id")
    @Mapping(target = "attachId", source = "attachId.id")
    @Mapping(target = "fileId", source = "fileId.id")
    @Mapping(target = "categoryIds", ignore = true)
    @Mapping(target = "hashTagIds", ignore = true)
    PostEntity domainToEntity(Post post);

    @Mapping(target = "postId.id", source = "postId")
    @Mapping(target = "memberId.id", source = "memberId")
    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "fileId.id", source = "fileId")
    @Mapping(target = "categoryIds", ignore = true)
    @Mapping(target = "hashTagIds", ignore = true)
    Post entityToDomain(PostEntity savePostEntity);

    CreateContentResponseDTO domainToCreateResponseDTO(Post savePost);

    @IterableMapping(elementTargetType = Post.class)
    Post dtoToDomain(CreateContentRequestDTO createContentRequestDto);

    // 응답 도메인을 회원가입 응답 DTO로 변환
    FindContentResponseDTO domainToResponseDTO(Post post);

}
