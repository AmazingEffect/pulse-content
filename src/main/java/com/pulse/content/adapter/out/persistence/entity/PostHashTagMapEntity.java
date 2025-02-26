package com.pulse.content.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "postHashTagMap")
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTagMapEntity extends BaseEntity{

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
}
