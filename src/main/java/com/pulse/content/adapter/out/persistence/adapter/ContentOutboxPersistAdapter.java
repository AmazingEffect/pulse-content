package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.ContentOutboxEntity;
import com.pulse.content.adapter.out.persistence.repository.ContentOutboxRepository;
import com.pulse.content.application.port.out.CreateContentOutboxPort;
import com.pulse.content.application.port.out.FindContentOutboxPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.ContentOutbox;
import com.pulse.content.mapper.ContentOutboxMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ContentOutboxPersistAdapter implements FindContentOutboxPort, CreateContentOutboxPort {

    private final ContentOutboxRepository contentOutboxRepository;
    private final ContentOutboxMapper contentOutboxMapper;


    /**
     * @param outbox MemberOutbox
     * @return 저장된 MemberOutbox의 ID
     * @apiNote MemberOutbox 도메인을 저장합니다.
     */
    @Override
    public Long saveContentOutboxEvent(ContentOutbox outbox) {
        ContentOutboxEntity entity = contentOutboxMapper.domainToEntity(outbox);
        ContentOutboxEntity savedEntity = contentOutboxRepository.save(entity);
        return savedEntity.getId();
    }


    /**
     * @param payload   MemberOutbox의 payload
     * @param eventType MemberOutbox의 eventType
     * @return 조회된 MemberOutbox
     * @apiNote MemberOutbox를 payload와 eventType으로 조회합니다.
     */
    @Override
    public ContentOutbox findContentOutboxBy(Long payload, String eventType) {
        ContentOutboxEntity entity = contentOutboxRepository.findByPayloadAndEventType(payload, eventType)
                .orElseThrow(() -> new IllegalArgumentException("MemberOutbox not found"));
        return contentOutboxMapper.entityToDomain(entity);
    }

}
