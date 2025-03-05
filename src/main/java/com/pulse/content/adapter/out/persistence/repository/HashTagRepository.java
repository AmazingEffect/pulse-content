package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {
    Optional<HashTagEntity> findByName(String name);
    List<HashTagEntity> findByNameIn(List<String> names);
}
