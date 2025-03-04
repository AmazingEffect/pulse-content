package com.pulse.content.domain.map;

import com.pulse.content.domain.Category;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.key.ContentCategoryMapId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentCategoryMap {

    private ContentCategoryMapId contentCategoryMapId;
    private Content content;
    private Category category;

    public static ContentCategoryMap of(Content content, Category category) {
        return ContentCategoryMap.builder()
                .content(content)
                .category(category)
                .build();
    }
}
