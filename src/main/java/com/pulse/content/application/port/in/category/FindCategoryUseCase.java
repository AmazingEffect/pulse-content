package com.pulse.content.application.port.in.category;

import com.pulse.content.domain.Category;

import java.util.List;

public interface FindCategoryUseCase {
    Category findById(Long categoryId);
    List<Category> findCategoriesByIds(List<Long> categoryIds);
}
