package com.pulse.content.common.enumerate;

import lombok.Getter;

@Getter
public enum ContentVisibility {
    OPEN("OPEN", "공개"),
    CLOSE("CLOSE", "비공개");

    private final String code;
    private final String description;

    ContentVisibility(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
