package com.pulse.content.adapter.out.persistence.repository.map;

import com.pulse.content.adapter.out.persistence.entity.map.PostHashTagMapEntity;
import com.pulse.content.domain.key.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHashTagMapRepository extends JpaRepository<PostHashTagMapEntity, Long> {
    List<PostHashTagMapEntity> findByPostEntity_PostId(PostId postId);
}
