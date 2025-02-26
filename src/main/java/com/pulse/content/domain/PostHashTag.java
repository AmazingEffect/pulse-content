package com.pulse.content.domain;

import com.pulse.content.domain.key.PostHashTagId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostHashTag {
    private PostHashTagId id;
    private Post post;
    private HashTag hashTag;

    public static PostHashTag of(PostHashTagId id, Post post, HashTag hashTag) {
        return PostHashTag.builder()
                .id(id)
                .post(post)
                .hashTag(hashTag)
                .build();
    }
}
