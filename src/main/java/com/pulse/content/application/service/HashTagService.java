package com.pulse.content.application.service;

import com.pulse.content.application.port.in.hashTag.CreateHashTagUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HashTagService implements CreateHashTagUseCase {

    private final CreateHashTagPort createHashTagPort;

    /**
     * HashTag 저장 메서드
     * @param hashTag - 저장할 HashTag 데이터
     * @return 저장한 HashTag
     */
    @Transactional
    @Override
    public HashTag create(HashTag hashTag) {
        return createHashTagPort.create(hashTag);
    }
}
