package com.pulse.content.adapter.out.persistence.repository.map;

import com.pulse.content.adapter.out.persistence.entity.map.ContentCategoryMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCategoryRepository extends JpaRepository<ContentCategoryMapEntity, Long> {
}
