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
import org.springframework.util.ObjectUtils;

import java.util.List;

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
     * @param createContentRequestDto
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

        hashTagNames.forEach(hashTagName -> {
            // 해시태그 조회
            HashTag hashTag = findHashTagPort.findByName(hashTagName);

            // 존재하지 않을 경우 해시태그 저장
            if (ObjectUtils.isEmpty(hashTag)) {
                hashTag = HashTag.of(hashTagName);
                createHashTagPort.create(hashTag);
            }

            // 해시태그 맵 저장
            ContentHashTagMap contentHashTagMap = ContentHashTagMap.of(content, hashTag);
            createContentHashTagMapPort.create(contentHashTagMap);
        });


        // todo: 카테고리
        List<Long> categoryIds = createContentRequestDto.getCategoryIds();
        // 카테고리 조회
        List<Category> categories = findCategoryPort.findCategoriesByIds(categoryIds);
        categories.forEach(category -> {
            // ContentCategoryMap 저장
            ContentCategoryMap contentCategoryMap = ContentCategoryMap.of(createdContent, category);
            createContentCategoryMapPort.create(contentCategoryMap);
        });

        return contentMapper.domainToCreateResponseDTO(createdContent);
    }

    @Override
    public FindContentResponseDTO findContent(ContentId contentId) {
        Content findContent = findContentPort.findContent(contentId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findContent);
    }
}
