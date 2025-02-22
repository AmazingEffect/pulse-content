package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
