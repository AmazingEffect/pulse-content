package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import com.pulse.content.adapter.out.persistence.repository.ContentAttachmentRepository;
import com.pulse.content.application.port.out.attachment.CreateContentAttachmentPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.vo.ContentAttachment;
import com.pulse.content.mapper.ContentAttachmentMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ContentAttachmentPersistAdapter implements CreateContentAttachmentPort {

    private final ContentAttachmentRepository contentAttachmentRepository;
    private final ContentAttachmentMapper contentAttachmentMapper;

    /**
     * ContentAttachment 리스트 저장
     * @param contentAttachments - 저장할 ContentAttachment 리스트
     * @return 저장된 ContentAttachment 리스트
     */
    @Override
    public List<ContentAttachment> createAll(List<ContentAttachment> contentAttachments) {
        List<ContentAttachmentEntity> contentAttachmentEntities = contentAttachments.stream()
                .map(contentAttachmentMapper::domainToEntity)
                .toList();

        List<ContentAttachmentEntity> createdContentAttachmentEntities = contentAttachmentRepository.saveAll(contentAttachmentEntities);

        return createdContentAttachmentEntities.stream()
                .map(contentAttachmentMapper::entityToDomain)
                .toList();
    }
}
