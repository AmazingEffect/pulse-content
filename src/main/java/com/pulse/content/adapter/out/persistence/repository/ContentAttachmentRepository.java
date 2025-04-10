package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentAttachmentRepository extends JpaRepository<ContentAttachmentEntity, Long> {
    void deleteAllByContentEntity_ContentId(Long contentId);
}
