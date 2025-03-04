package com.pulse.content.domain.map;

import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.Post;
import com.pulse.content.domain.key.PostHashTagMapId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostHashTagMap {
    private PostHashTagMapId id;
    private Post post;
    private HashTag hashTag;

    public static PostHashTagMap of(Post post, HashTag hashTag) {
        return PostHashTagMap.builder()
                .post(post)
                .hashTag(hashTag)
                .build();
    }
}
