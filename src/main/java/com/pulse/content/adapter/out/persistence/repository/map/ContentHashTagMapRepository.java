package com.pulse.content.adapter.out.persistence.repository.map;

import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.adapter.out.persistence.entity.map.ContentHashTagMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentHashTagMapRepository extends JpaRepository<ContentHashTagMapEntity, Long> {
    List<ContentHashTagMapEntity> findAllByContentEntity_ContentId(Long contentId);
}
