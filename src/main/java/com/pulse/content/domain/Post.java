package com.pulse.content.domain;

import com.pulse.content.adapter.out.persistence.entity.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 게시글
 * Comment, PostTagMap, Location 연관 관계에서 각각 일괄 로딩(batch fetching)을 적용받아 성능을 최적화할 수 있도록 설정
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post {

    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String imageUrl;
    private Category category;
    private Set<Comment> comments;
    private Set<PostTagMap> postTags;
    private Set<Location> locations;

}
