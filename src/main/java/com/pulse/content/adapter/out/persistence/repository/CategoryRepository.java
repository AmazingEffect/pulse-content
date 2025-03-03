package com.pulse.content.adapter.out.persistence.repository;

import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByCategoryIdIn(List<Long> categoryIds);
}
