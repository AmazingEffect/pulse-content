package com.pulse.content.application.service;

import com.pulse.content.application.port.in.category.FindCategoryUseCase;
import com.pulse.content.application.port.out.category.FindCategoryPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.Category;
import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService implements FindCategoryUseCase {

    private final FindCategoryPort findCategoryPort;

    /**
     * 카테고리 id(PK)를 통해 카테고리 조회
     * @param categoryId - 조회할 카테고리 id
     * @return 카테고리
     */
    @Override
    public Category findById(Long categoryId) {
        Category category = findCategoryPort.findById(categoryId);

        if (ObjectUtils.isEmpty(category)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return category;
    }

    /**
     * 카테고리 id(PK)를 통해 카테고리 리스트 조회
     * @param categoryIds - 조회할 카테고리 id 리스트
     * @return 카테고리 리스트
     */
    @Override
    public List<Category> findCategoriesByIds(List<Long> categoryIds) {
        List<Category> categories = findCategoryPort.findCategoriesByIds(categoryIds);

        if (categories.isEmpty()) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }

        return categories;
    }
}
