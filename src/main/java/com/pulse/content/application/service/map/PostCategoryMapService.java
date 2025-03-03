package com.pulse.content.application.service.map;

import com.pulse.content.application.port.in.map.CreatePostCategoryMapUseCase;
import com.pulse.content.application.port.out.map.CreatePostCategoryMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.map.PostCategoryMap;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCategoryMapService implements CreatePostCategoryMapUseCase {

    private final CreatePostCategoryMapPort createPostCategoryMapPort;

    /**
     * PostCategoryMap 저장
     * @param postCategoryMap - PostCategoryMap 객체
     * @return - 저장된 PostCategoryMap
     */
    @Transactional
    @Override
    public PostCategoryMap create(PostCategoryMap postCategoryMap) {
        return createPostCategoryMapPort.create(postCategoryMap);
    }
}
