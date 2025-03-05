package com.pulse.content.application.service.map;

import com.pulse.content.application.port.FindPostCategoryMapPort;
import com.pulse.content.application.port.in.map.FindPostCategoryMapUseCase;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostCategoryMap;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCategoryMapService implements FindPostCategoryMapUseCase {

    private final FindPostCategoryMapPort findPostCategoryMapPort;

    /**
     * @apiNote postId에 맞는 PostCategoryMap 목록 조회
     * @param postId
     * @return
     */
    @Override
    public List<PostCategoryMap> findCategoriesByPostId(PostId postId) {
        return findPostCategoryMapPort.findCategoriesByPostId(postId);
    }
}
