package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "quote")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuoteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_post_id")
    private Long originalPostId;    // 원본 게시글 id

    @Column(name = "citing_post_id")
    private Long citingPostId;      // 인용한 게시글 id

    // factory method
    public static QuoteEntity of(Long id, Long originalPostId, Long citingPostId) {
        return QuoteEntity.builder()
                .id(id)
                .originalPostId(originalPostId)
                .citingPostId(citingPostId)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteEntity that = (QuoteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
