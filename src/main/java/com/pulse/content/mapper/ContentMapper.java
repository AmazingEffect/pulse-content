package com.pulse.content.mapper;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.DeleteContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.domain.vo.ContentDetail;
import org.mapstruct.*;

import java.util.List;

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

    DeleteContentResponseDTO domainToDeleteResponseDTO(Content deletedContent);

    // ContentEntity + hashTags + contentHashTagMaps로 Content 도메인을 만들어주는 커스텀 메서드
    default Content entityToDomain(ContentEntity entity, List<String> hashTags, List<ContentHashTagMap> contentHashTagMaps) {
        return Content.of(
                new ContentId(entity.getContentId()),
                new MemberId(entity.getMemberId()),
                hashTags,
                contentHashTagMaps,
                null, // contentCategories는 현재 로직에서 필요 없으면 null
                ContentDetail.of(entity.getTitle(), entity.getText()),
                entity.getContentStatus(),
                entity.getContentVisibility()
        );
    }

}
