package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
}
