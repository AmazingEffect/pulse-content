package com.pulse.content.application.service.map;

import com.pulse.content.application.port.in.map.CreateContentCategoryMapUseCase;
import com.pulse.content.application.port.out.map.CreateContentCategoryMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.map.ContentCategoryMap;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentCategoryMapService implements CreateContentCategoryMapUseCase {

    private final CreateContentCategoryMapPort createContentCategoryMapPort;

    /**
     * ContentCategoryMap 저장
     * @param contentCategoryMap - ContentCategoryMap 객체
     * @return - 저장된 ContentCategoryMap
     */
    @Transactional
    @Override
    public ContentCategoryMap create(ContentCategoryMap contentCategoryMap) {
        return createContentCategoryMapPort.create(contentCategoryMap);
    }
}
