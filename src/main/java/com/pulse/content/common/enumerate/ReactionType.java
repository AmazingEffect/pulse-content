package com.pulse.content.common.enumerate;

import lombok.Getter;

@Getter
public enum ReactionType {

    GOOD("GOOD", "좋아요"),
    BAD("BAD", "싫어요");

    private final String code;
    private final String description;

    ReactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
