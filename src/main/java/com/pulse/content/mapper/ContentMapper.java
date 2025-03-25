package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.vo.ContentDetail;
import org.mapstruct.*;
/**
 * componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다. (외부에서 주입받아서 사용하면 된다.)
 * unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ContentDetail.class}
)
public interface ContentMapper {

    @Mapping(target = "contentId", source = "contentId.id")
    @Mapping(target = "memberId", source = "memberId.id")
    @Mapping(target = "title", source = "contentDetail.title")
    @Mapping(target = "text", source = "contentDetail.text")
    ContentEntity domainToEntity(Content content);

    @Mapping(target = "contentId.id", source = "contentId")
    @Mapping(target = "memberId.id", source = "memberId")
    @Mapping(target = "contentDetail", expression = "java(ContentDetail.of(entity.getTitle(), entity.getText()))")
    Content entityToDomain(ContentEntity entity);

    CreateContentResponseDTO domainToCreateResponseDTO(Content saveContent);

    @IterableMapping(elementTargetType = Content.class)
    Content createRequestDtoToDomain(CreateContentRequestDTO createContentRequestDto);

    // 응답 도메인을 회원가입 응답 DTO로 변환
    FindContentResponseDTO domainToResponseDTO(Content content);

    UpdateContentResponseDTO domainToUpdateResponseDTO(Content updatedContent);
}
