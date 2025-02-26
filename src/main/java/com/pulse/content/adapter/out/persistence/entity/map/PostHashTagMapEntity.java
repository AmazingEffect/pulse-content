package com.pulse.content.adapter.out.persistence.entity.map;

import com.pulse.content.adapter.out.persistence.entity.BaseEntity;
import com.pulse.content.adapter.out.persistence.entity.HashTagEntity;
import com.pulse.content.adapter.out.persistence.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Table(name = "post_hashtag_map")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTagMapEntity extends BaseEntity {

    @Id
    @Column(name = "post_hashtag_map_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTagEntity hashTagEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostHashTagMapEntity that = (PostHashTagMapEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(postEntity, that.postEntity) && Objects.equals(hashTagEntity, that.hashTagEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postEntity, hashTagEntity);
    }
}
