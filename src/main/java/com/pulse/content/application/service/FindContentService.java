package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.application.port.FindContentPort;
import com.pulse.content.application.port.FindPostCategoryMapPort;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;
import com.pulse.content.mapper.ContentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindContentService implements FindContentUseCase {

    private final FindPostCategoryMapPort findPostCategoryMapPort;
    private final ContentMapper contentMapper;
    private final FindContentPort findContentPort;

    /**
     * @apiNote 게시글 단건 조회
     * @param postId 게시글 아이디
     * @return
     */
    @Override
    public FindContentResponseDTO findContent(PostId postId) {
        Post findPost = findContentPort.findContent(postId); // 행위에 대한 이름으로

        // map 객체 조회
        List<PostCategoryMap> findPostCategoryMap = findPostCategoryMapPort.findCategoriesByPostId(postId);

        // 카테고리 조회(위에서 찾은 객체로 카테고리 조회)

        // 해시태그 조회
        return contentMapper.domainToResponseDTO(findPost);
    }
}
