package com.pulse.content.adapter.out.persistence.entity;

import lombok.Getter;

@Getter
public enum TargetType {
    POST("POST", "게시글"),
    COMMENT("COMMENT", "댓글");

    private final String code;
    private final String description;

    TargetType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
