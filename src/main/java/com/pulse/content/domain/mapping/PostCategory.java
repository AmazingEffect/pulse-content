package com.pulse.content.domain.mapping;

import com.pulse.content.domain.Category;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.mapping.key.PostCategoryId;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategory {
    private PostCategoryId id;
    private Post post;
    private Category category;

    public static PostCategory of(Long id, Post post, Category category) {
        return PostCategory.builder()
                .id(id)
                .post(post)
                .category(category)
                .build();
    }
}
