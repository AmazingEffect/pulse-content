package com.pulse.content.common.enumerate;

import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

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

    /**
     * 콘텐츠 공개 범위 유효성 검사
     * @param contentVisibility 콘텐츠 공개 범위
     */
    public static void contentVisibilityValidation(ContentVisibility contentVisibility) {
        if (ObjectUtils.isEmpty(contentVisibility)) {
            throw new ContentException(ErrorCode.CONTENT_VISIBILITY_REQUIRED);
        }
    }
}
