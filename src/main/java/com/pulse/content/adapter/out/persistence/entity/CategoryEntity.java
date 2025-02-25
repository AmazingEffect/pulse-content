package com.pulse.content.adapter.out.persistence.entity;

import com.pulse.content.adapter.out.persistence.entity.mapping.PostCategoryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "category")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;    // 카테고리 이름

    @Column(name = "text")
    private String text;    // 설명 내용

    @OneToMany(mappedBy = "category")
    private List<PostCategoryEntity> postCategories;

    // factory method
    public static CategoryEntity of(Long id, String name, String text) {
        return CategoryEntity.builder()
                .id(id)
                .name(name)
                .text(text)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
