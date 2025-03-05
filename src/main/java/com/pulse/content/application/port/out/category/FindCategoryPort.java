package com.pulse.content.application.port.out.category;

import com.pulse.content.domain.Category;

import java.util.List;

public interface FindCategoryPort {
    Category findById(Long categoryId);
    List<Category> findCategoriesByIds(List<Long> categoryIds);
}
