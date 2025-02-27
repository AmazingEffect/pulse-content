package com.pulse.content.adapter.out.persistence.entity.map;

import com.pulse.content.adapter.out.persistence.entity.BaseEntity;
import com.pulse.content.adapter.out.persistence.entity.CategoryEntity;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Builder
@Table(name = "post_category_map")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategoryMapEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_category_map_id")
    private Long postCategoryMapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;


    // factory method
    public static PostCategoryMapEntity of(PostEntity postEntity, CategoryEntity categoryEntity) {
        return PostCategoryMapEntity.builder()
                .postEntity(postEntity)
                .categoryEntity(categoryEntity)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCategoryMapEntity that = (PostCategoryMapEntity) o;
        return Objects.equals(postCategoryMapId, that.postCategoryMapId) && Objects.equals(postEntity, that.postEntity) && Objects.equals(categoryEntity, that.categoryEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postCategoryMapId, postEntity, categoryEntity);
    }
}
