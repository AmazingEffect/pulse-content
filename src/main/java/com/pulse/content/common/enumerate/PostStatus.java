package com.pulse.content.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {

    PUBLIC("PUBLIC", "공유"),
    PRIVATE("PRIVATE", "비공개"),
    DELETED("DELETED", "삭제");

    private final String code;
    private final String description;

}
