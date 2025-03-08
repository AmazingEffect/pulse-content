package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.request.UpdateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.application.port.in.content.UpdateContentUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.HashTag.FindHashTagPort;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.application.port.out.content.UpdateContentPort;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.application.port.out.map.DeleteContentHashTagMapPort;
import com.pulse.content.application.port.out.map.FindContentHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService implements CreateContentsUseCase, FindContentUseCase, UpdateContentUseCase {

    private final ContentMapper contentMapper;

    private final CreateContentPort createContentPort;
    private final CreateHashTagPort createHashTagPort;
    private final CreateContentHashTagMapPort createContentHashTagMapPort;

    private final FindContentPort findContentPort;
    private final FindHashTagPort findHashTagPort;
    private final FindContentHashTagMapPort findContentHashTagMapPort;

    private final UpdateContentPort updateContentPort;

    private final DeleteContentHashTagMapPort deleteContentHashTagMapPort;

    /**
     * @apiNote 게시글 작성 및 관련 데이터 저장
     * -> hashtag, hashtag-map, category-map
     * @param createContentRequestDto - 저장할 콘텐츠 정보
     * @return 저장된 콘텐츠 정보
     */
    @Transactional
    @Override
    public CreateContentResponseDTO create(CreateContentRequestDTO createContentRequestDto) {
        // todo: 게시글
        Content content = contentMapper.createRequestDtoToDomain(createContentRequestDto);
        // 게시글 상태(ContentStatus) 세팅
        content.changeContentStatus(ContentStatus.PUBLISHED);
        // 게시글 저장
        Content createdContent = createContentPort.create(content);

        // 해시태그 목록 저장
        List<String> hashTagNames = createContentRequestDto.getHashTagNames();
        List<HashTag> existingHashTags = createHashTags(hashTagNames);

        // 해시태그 맵 목록 저장
        createContentHashTagMaps(existingHashTags, createdContent);

        return contentMapper.domainToCreateResponseDTO(createdContent);
    }

    /**
     * 게시글 수정 및 관련 데이터 수정
     * @param updateContentRequestDTO - 수정할 콘텐츠 정보
     * @return 수정된 콘텐츠 정보
     */
    @Transactional
    @Override
    public UpdateContentResponseDTO update(UpdateContentRequestDTO updateContentRequestDTO) {
        ContentId contentId = updateContentRequestDTO.getContentId();
        if (ObjectUtils.isEmpty(contentId) || ObjectUtils.isEmpty(contentId.id())) {
            throw new ContentException(ErrorCode.CONTENT_ID_REQUIRED);
        }

        // 콘텐츠 조회
        Content content = findContentPort.findContent(contentId);

        if (ObjectUtils.isEmpty(content)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }

        // 작성자와 수정자 아이디 비교
        MemberId wirteMemberId = content.getMemberId();
        MemberId updateMemberId = updateContentRequestDTO.getMemberId();
        if (!wirteMemberId.equals(updateMemberId)) {
            throw new ContentException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        // 콘텐츠 제목 및 내용 변경
        String title = updateContentRequestDTO.getTitle();
        String contentText = updateContentRequestDTO.getText();
        if (ObjectUtils.isEmpty(title) || ObjectUtils.isEmpty(contentText)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
        content.changeContentDetail(ContentDetail.of(title, contentText));

        // 콘텐츠 공개 범위 변경
        ContentVisibility contentVisibility = updateContentRequestDTO.getContentVisibility();
        if (ObjectUtils.isEmpty(contentVisibility)) {
            throw new ContentException(ErrorCode.CONTENT_VISIBILITY_REQUIRED);
        }
        content.changeContentVisibility(contentVisibility);

        // 콘텐츠 수정
        Content updatedContent = updateContentPort.update(content);

        List<String> hashTagNames = updateContentRequestDTO.getHashTagNames();
        List<ContentHashTagMap> contentHashTagMaps = findContentHashTagMapPort.findByContentId(contentId.id());
        if (ObjectUtils.isEmpty(contentHashTagMaps)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }

        // 삭제할 대상
        List<Long> deleteContentHashTagMap = contentHashTagMaps.stream()
                .filter(map -> !hashTagNames.contains(map.getHashTag().getName()))
                .map(map -> map.getContentHashTagMapId().id())
                .toList();
        // 콘텐츠 해시태그 맵 삭제
        deleteContentHashTagMapPort.deleteAll(deleteContentHashTagMap);

        Set<String> existingNames = contentHashTagMaps.stream()
                .map(map -> map.getHashTag().getName())
                .collect(Collectors.toSet());

        // 새로운 해시태그 저장
        Set<String> toInsert = new HashSet<>(hashTagNames);
        toInsert.removeAll(existingNames); // existingNames에 있는 것 제거 -> 남는 것은 추가 해야 하는 것
        List<HashTag> existingHashTags = createHashTags(hashTagNames);  // 해시태그 저장
        createContentHashTagMaps(existingHashTags, content);    // 해시태그 맵 저장

        return contentMapper.domainToUpdateResponseDTO(updatedContent);
    }

    /**
     * @apiNote 해시태그명으로 해시태그 조회 후, 저장 되어 있지 않은 해시태그명만 저장
     * @param hashTagNames - 유저가 입력한 해시태그명
     * @return 저장된 해시태그 목록
     */
    private List<HashTag> createHashTags(List<String> hashTagNames) {
        // 이미 저장된 해시태그 목록
        List<HashTag> existingHashTags = findHashTagPort.findByNames(hashTagNames);
        // 새로 저장할 해시태그 목록(저장 되어 있지 않은 해시태그명)
        List<HashTag> newHashTags = hashTagNames.stream()
                .filter(name -> existingHashTags.stream().noneMatch(hashTag -> hashTag.getName().equals(name)))
                .map(HashTag::of).collect(Collectors.toList());
        // 해시태그 목록 저장
        List<HashTag> createdHashTags = createHashTagPort.createAll(newHashTags);
        existingHashTags.addAll(createdHashTags);
        return existingHashTags;
    }

    /**
     * @apiNote 해시태그와 콘텐츠로 ContentHashTagMap 저장
     * @param existingHashTags - 기존에 저장 되어 있는 해시태그
     * @param createdContent - 저장된 게시글
     * @return 저장된 해시태그 맵 목록
     */
    private List<ContentHashTagMap> createContentHashTagMaps(List<HashTag> existingHashTags, Content createdContent) {
        List<ContentHashTagMap> contentHashTagMaps = existingHashTags.stream()
                .map(hashTag -> ContentHashTagMap.of(createdContent, hashTag))
                .toList();
        return createContentHashTagMapPort.createAll(contentHashTagMaps);
    }

    @Override
    public FindContentResponseDTO findContent(ContentId contentId) {
        Content findContent = findContentPort.findContent(contentId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findContent);
    }
}
