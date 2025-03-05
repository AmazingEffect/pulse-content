package com.pulse.content.common.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentStatus {

    DRAFT("DRAFT", "임시 저장"),       // 임시 저장 상태
    PUBLISHED("PUBLISHED", "게시"),   // 게시가 완료된 상태
    HIDDEN("HIDDEN", "숨김"),         // 숨김(보관)된 상태
    DELETED("DELETED", "삭제");       // 삭제된 상태

    private final String code;
    private final String description;
}
