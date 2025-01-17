package com.pulse.content.domain;

import com.pulse.content.domain.key.QuoteId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Quote {

    private QuoteId quoteId;

    private Long originalPostId;    // 원본 게시글 id

    private Long citingPostId;      // 인용한 게시글 id
}
