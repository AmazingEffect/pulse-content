package com.pulse.content.mapper.helper;

import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.domain.Category;
import com.pulse.content.domain.Content;
import com.pulse.content.mapper.CategoryMapper;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperHelper {

    private final CategoryMapper categoryMapper;

    /**
     * 카테고리 도메인 객체를 카테고리 엔티티 객체로 변환
     * @param category - 카테고리 도메인
     * @return 카테고리 엔티티
     */
    @Named("categoryDomainToEntity")
    public CategoryEntity categoryDomainToEntity(Category category) {
        return categoryMapper.domainToEntity(category);
    }


    /**
     * 카테고리 엔티티 객체를 카테고리 도메인 객체로 변환
     * @param categoryEntity - 카테고리 엔티티
     * @return 카테고리 도메인
     */
    @Named("categoryEntityToDomain")
    public Category categoryEntityToDomain(CategoryEntity categoryEntity) {
        return categoryMapper.entityToDomain(categoryEntity);
    }
}
