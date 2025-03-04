package com.pulse.content.application.service.map;

import com.pulse.content.application.port.in.map.CreatePostHashTagMapUseCase;
import com.pulse.content.application.port.out.map.CreatePostHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.map.PostHashTagMap;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostHashTagMapService implements CreatePostHashTagMapUseCase {

    private final CreatePostHashTagMapPort createPostHashTagMapPort;

    /**
     * PostHashTag 저장 메서드
     * @param postHashTagMap - 저장할 PostHashTagMap 도메인
     * @return 저장된 PostHashTagMap
     */
    @Transactional
    @Override
    public PostHashTagMap create(PostHashTagMap postHashTagMap) {
        return createPostHashTagMapPort.create(postHashTagMap);
    }
}
