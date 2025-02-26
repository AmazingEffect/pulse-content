package com.pulse.content.adapter.out.persistence.repository.mapping;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategoryMapEntity, Long> {
}
