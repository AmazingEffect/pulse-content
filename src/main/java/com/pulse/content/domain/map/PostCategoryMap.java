package com.pulse.content.domain.map;

import com.pulse.content.domain.Category;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostCategoryMapId;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCategoryMap {

    private PostCategoryMapId postCategoryMapId;
    private Post post;
    private Category category;

    public static PostCategoryMap of(Post post, Category category) {
        return PostCategoryMap.builder()
                .post(post)
                .category(category)
                .build();
    }
}
