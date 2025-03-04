package com.pulse.content.application.service;

import com.pulse.content.application.port.in.hashTag.CreateHashTagUseCase;
import com.pulse.content.application.port.in.hashTag.FindHashTagUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.HashTag.FindHashTagPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HashTagService implements CreateHashTagUseCase, FindHashTagUseCase {

    private final CreateHashTagPort createHashTagPort;
    private final FindHashTagPort findHashTagPort;

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

    /**
     * 해시태그명으로 해시태그 조회
     * @param name - 조회할 해시태그명
     * @return 조회된 해시태그
     */
    @Override
    public HashTag findByName(String name) {
        return findHashTagPort.findByName(name);
    }
}
