package com.pulse.content.common.enumerate;

import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MessageStatus {

    PENDING("PENDING", "대기"),
    PROCESSED("PROCESSED", "처리완료"),
    SUCCESS("SUCCESS", "성공"),
    FAIL("FAIL", "실패");

    private final String code;
    private final String description;


    /**
     * 코드로 MessageStatus 찾기
     *
     * @param code 코드
     * @return MessageStatus
     */
    public static MessageStatus of(String code) {
        return Arrays.stream(MessageStatus.values())
                .filter(messageStatus -> messageStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ContentException(ErrorCode.INVALID_MESSAGE_STATUS));
    }

}
