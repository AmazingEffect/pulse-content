package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import com.pulse.content.domain.Post;
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
    // 응답 도메인을 회원가입 응답 DTO로 변환
    FindContentResponseDTO domainToResponseDTO(Post post);

    @Mapping(target = "postId.id", source = "postId")
    @Mapping(target = "memberId.id", source = "memberId")
    @Mapping(target = "attachId.id", source = "attachId")
    @Mapping(target = "fileId.id", source = "fileId")
    Post entityToDomain(PostEntity postEntity);
}
