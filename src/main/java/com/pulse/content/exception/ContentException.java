package com.pulse.content.exception;

import lombok.Getter;

@Getter
public class ContentException extends RuntimeException {

    private final ErrorCode errorCode;

    public ContentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ContentException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}
