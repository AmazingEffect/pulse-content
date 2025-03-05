package com.pulse.content.adapter.out.persistence.adapter;

import com.pulse.content.adapter.out.persistence.repository.CategoryRepository;
import com.pulse.content.application.port.out.category.FindCategoryPort;
import com.pulse.content.common.annotation.PersistenceAdapter;
import com.pulse.content.domain.Category;
import com.pulse.content.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class CategoryPersistAdapter implements FindCategoryPort {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /**
     * 카테고리 id(PK)를 통해 카테고리 조회
     * @param categoryId - 조회할 카테고리 id
     * @return 카테고리
     */
    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::entityToDomain)
                .orElse(null);
    }

    /**
     * 카테고리 id(PK)를 통해 카테고리 리스트 조회
     * @param categoryIds - 조회할 카테고리 id 리스트
     * @return 카테고리 리스트
     */
    @Override
    public List<Category> findCategoriesByIds(List<Long> categoryIds) {
        return categoryRepository.findByCategoryIdIn(categoryIds).stream()
                .map(categoryMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
