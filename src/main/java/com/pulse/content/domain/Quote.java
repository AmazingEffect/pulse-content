package com.pulse.content.domain;

import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.key.QuoteId;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Quote {

    private QuoteId quoteId;
    private ContentId originalContentId;    // 원본 게시글 id
    private ContentId citingContentId;      // 인용한 게시글 id

    // factory method
    public static Quote of(ContentId originalContentId, ContentId citingContentId) {
        return Quote.builder()
                .originalContentId(originalContentId)
                .citingContentId(citingContentId)
                .build();
    }
}
