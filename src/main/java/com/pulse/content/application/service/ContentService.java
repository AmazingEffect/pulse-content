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
import com.pulse.content.domain.Category;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentCategoryMap;
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

        // todo: 해시태그
        List<String> hashTagNames = createContentRequestDto.getHashTagNames();

        // 이미 저장된 해시태그 목록
        List<HashTag> existingHashTags = findHashTagPort.findByNames(hashTagNames);
        // 새로 저장할 해시태그 목록(저장 되어 있지 않은 해시태그명)
        List<HashTag> newHashTags = hashTagNames.stream()
                .filter(name -> existingHashTags.stream().noneMatch(hashTag -> hashTag.getName().equals(name)))
                .map(HashTag::of).collect(Collectors.toList());
        // 해시태그 목록 저장
        List<HashTag> createdHashTags = createHashTagPort.createAll(newHashTags);
        existingHashTags.addAll(createdHashTags);

        // 해시태그 맵 저장
        List<ContentHashTagMap> contentHashTagMaps = existingHashTags.stream()
                .map(hashTag -> ContentHashTagMap.of(content, hashTag))
                .toList();
        createContentHashTagMapPort.createAll(contentHashTagMaps);


        // todo: 카테고리
        List<Long> categoryIds = createContentRequestDto.getCategoryIds();
        // 카테고리 조회
        List<Category> categories = findCategoryPort.findCategoriesByIds(categoryIds);
        // 카테고리 맵 저장
        List<ContentCategoryMap> contentCategoryMaps = categories.stream()
                .map(category -> ContentCategoryMap.of(content, category))
                .toList();
        createContentCategoryMapPort.createAll(contentCategoryMaps);

        return contentMapper.domainToCreateResponseDTO(createdContent);
    }

    @Override
    public FindContentResponseDTO findContent(ContentId contentId) {
        Content findContent = findContentPort.findContent(contentId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findContent);
    }
}
