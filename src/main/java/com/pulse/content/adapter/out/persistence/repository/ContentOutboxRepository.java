package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.ContentOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentOutboxRepository extends JpaRepository<ContentOutboxEntity, Long> {

    Optional<ContentOutboxEntity> findByPayloadAndEventType(Long id, String eventType);

}
