package com.pulse.content.common.enumerate;

import lombok.Getter;

@Getter
public enum PostVisibility {
    OPEN("OPEN", "공개"),
    CLOSE("CLOSE", "비공개");

    private final String code;
    private final String description;

    PostVisibility(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
