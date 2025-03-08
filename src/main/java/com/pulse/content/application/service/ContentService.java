package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.HashTag.FindHashTagPort;
import com.pulse.content.application.port.out.category.FindCategoryPort;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.application.port.out.map.CreateContentCategoryMapPort;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService implements CreateContentsUseCase, FindContentUseCase {

    private final ContentMapper contentMapper;

    private final CreateContentPort createContentPort;
    private final CreateHashTagPort createHashTagPort;
    private final CreateContentCategoryMapPort createContentCategoryMapPort;
    private final CreateContentHashTagMapPort createContentHashTagMapPort;

    private final FindContentPort findContentPort;
    private final FindCategoryPort findCategoryPort;
    private final FindHashTagPort findHashTagPort;

    /**
     * @apiNote 게시글 작성 및 관련 데이터 저장
     * -> hashtag, hashtag-map, category-map
     * @param createContentRequestDto - 저장할 콘텐츠 정보
     * @return
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
     */
    private void createContentHashTagMaps(List<HashTag> existingHashTags, Content createdContent) {
        List<ContentHashTagMap> contentHashTagMaps = existingHashTags.stream()
                .map(hashTag -> ContentHashTagMap.of(createdContent, hashTag))
                .toList();
        createContentHashTagMapPort.createAll(contentHashTagMaps);
    }

    @Override
    public FindContentResponseDTO findContent(ContentId contentId) {
        Content findContent = findContentPort.findContent(contentId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findContent);
    }
}
