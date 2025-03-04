package com.pulse.content.application.service.map;

import com.pulse.content.application.port.in.map.CreateContentHashTagMapUseCase;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.map.ContentHashTagMap;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentHashTagMapService implements CreateContentHashTagMapUseCase {

    private final CreateContentHashTagMapPort createContentHashTagMapPort;

    /**
     * ContentHashTagMap 저장 메서드
     * @param contentHashTagMap - 저장할 ContentHashTagMap 도메인
     * @return 저장된 ContentHashTagMap
     */
    @Transactional
    @Override
    public ContentHashTagMap create(ContentHashTagMap contentHashTagMap) {
        return createContentHashTagMapPort.create(contentHashTagMap);
    }
}
