package com.pulse.content.domain;

import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.key.QuoteId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Quote {

    private QuoteId quoteId;

    private PostId originalPostId;    // 원본 게시글 id

    private PostId citingPostId;      // 인용한 게시글 id

    // factory method
    public static Quote of(QuoteId quoteId, PostId originalPostId, PostId citingPostId) {
        return Quote.builder()
                .quoteId(quoteId)
                .originalPostId(originalPostId)
                .citingPostId(citingPostId)
                .build();
    }
}
