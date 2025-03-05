package com.pulse.content.mapper;

import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "categoryId.id")
    CategoryEntity domainToEntity(Category category);

    @Mapping(target = "categoryId.id", source = "categoryId")
    Category entityToDomain(CategoryEntity categoryEntity);
}
