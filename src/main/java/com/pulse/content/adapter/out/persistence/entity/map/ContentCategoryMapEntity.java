package com.pulse.content.adapter.out.persistence.entity.map;

import com.pulse.content.adapter.out.persistence.entity.BaseEntity;
import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Table(name = "content_category_map")
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentCategoryMapEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_category_map_id")
    private Long contentCategoryMapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentEntity contentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;


    // factory method
    public static ContentCategoryMapEntity of(ContentEntity contentEntity, CategoryEntity categoryEntity) {
        return ContentCategoryMapEntity.builder()
                .contentEntity(contentEntity)
                .categoryEntity(categoryEntity)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentCategoryMapEntity that = (ContentCategoryMapEntity) o;
        return Objects.equals(contentCategoryMapId, that.contentCategoryMapId) && Objects.equals(contentEntity, that.contentEntity) && Objects.equals(categoryEntity, that.categoryEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentCategoryMapId, contentEntity, categoryEntity);
    }
}
