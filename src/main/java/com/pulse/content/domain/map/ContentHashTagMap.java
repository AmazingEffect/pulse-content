package com.pulse.content.domain.map;

import com.pulse.content.domain.Content;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.key.ContentHashTagMapId;
import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentHashTagMap {
    private ContentHashTagMapId contentHashTagMapId;
    private Content content;
    private HashTag hashTag;

    public static ContentHashTagMap of(Content content, HashTag hashTag) {
        return ContentHashTagMap.builder()
                .content(content)
                .hashTag(hashTag)
                .build();
    }
}
