package com.pulse.content.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TargetType {

    Post("POST", "게시글"),
    COMMENT("COMMENT", "댓글");

    private String code;
    private String description;
}
