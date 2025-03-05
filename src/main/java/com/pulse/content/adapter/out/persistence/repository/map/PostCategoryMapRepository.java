package com.pulse.content.adapter.out.persistence.repository.map;

import com.pulse.content.adapter.out.persistence.entity.map.PostCategoryMapEntity;
import com.pulse.content.domain.key.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCategoryMapRepository extends JpaRepository<PostCategoryMapEntity, Long> {
    List<PostCategoryMapEntity> findByPostEntity_PostId(PostId postId);
}
