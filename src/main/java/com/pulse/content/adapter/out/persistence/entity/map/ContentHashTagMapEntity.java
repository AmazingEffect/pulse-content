package com.pulse.content.adapter.out.persistence.entity.map;

import com.pulse.content.adapter.out.persistence.entity.BaseEntity;
import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "content_hashtag_map")
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentHashTagMapEntity extends BaseEntity {

    @Id
    @Column(name = "content_hashtag_map_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentHashTagMapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentEntity contentEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTagEntity hashTagEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentHashTagMapEntity that = (ContentHashTagMapEntity) o;
        return Objects.equals(contentHashTagMapId, that.contentHashTagMapId) && Objects.equals(contentEntity, that.contentEntity) && Objects.equals(hashTagEntity, that.hashTagEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentHashTagMapId, contentEntity, hashTagEntity);
    }
}
