package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.application.port.in.category.FindCategoryUseCase;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.application.port.in.hashTag.FindHashTagUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.application.port.out.map.CreatePostCategoryMapPort;
import com.pulse.content.application.port.out.map.CreatePostHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.common.enumerate.PostStatus;
import com.pulse.content.domain.Category;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.domain.map.PostHashTagMap;
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
    private final CreatePostCategoryMapPort createPostCategoryMapPort;
    private final CreatePostHashTagMapPort createPostHashTagMapPort;

    private final FindContentPort findContentPort;
    private final FindCategoryUseCase findCategoryUseCase;
    private final FindHashTagUseCase findHashTagUseCase;

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
        Post post = contentMapper.createRequestDtoToDomain(createContentRequestDto);
        // 게시글 상태(postStatus) 세팅
        post.changePostStatus(PostStatus.PUBLISHED);
        // 게시글 저장
        Post createdPost = createContentPort.create(post);

        // todo: 해시태그
        List<String> hashTagNames = createContentRequestDto.getHashTagNames();

        hashTagNames.forEach(hashTagName -> {
            // 해시태그 조회
            HashTag hashTag = findHashTagUseCase.findByName(hashTagName);

            // 존재하지 않을 경우 해시태그 저장
            if (ObjectUtils.isEmpty(hashTag)) {
                hashTag = HashTag.of(hashTagName);
                createHashTagPort.create(hashTag);
            }

            // 해시태그 맵 저장
            PostHashTagMap postHashTagMap = PostHashTagMap.of(post, hashTag);
            createPostHashTagMapPort.create(postHashTagMap);
        });


        // todo: 카테고리
        List<Long> categoryIds = createContentRequestDto.getCategoryIds();
        // 카테고리 조회
        List<Category> categories = findCategoryUseCase.findCategoriesByIds(categoryIds);
        categories.forEach(category -> {
            // PostCategoryMap 저장
            PostCategoryMap postCategoryMap = PostCategoryMap.of(createdPost, category);
            createPostCategoryMapPort.create(postCategoryMap);
        });

        return contentMapper.domainToCreateResponseDTO(createdPost);
    }

    @Override
    public FindContentResponseDTO findContent(PostId postId) {
        Post findPost = findContentPort.findContent(postId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findPost);
    }
}
