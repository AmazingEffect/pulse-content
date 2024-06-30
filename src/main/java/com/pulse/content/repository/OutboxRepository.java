package com.pulse.content.repository;

import com.pulse.content.entity.ContentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutboxRepository extends JpaRepository<ContentOutbox, Long> {

    Optional<ContentOutbox> findByEventIdAndEventType(Long id, String eventType);

}
