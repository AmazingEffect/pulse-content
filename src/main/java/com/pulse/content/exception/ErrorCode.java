package com.pulse.content.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common
    METHOD_NOT_ALLOWED("CON001", "Method not allowed"),
    ENTITY_NOT_FOUND("CON002", "Entity not found"),
    HANDLE_ACCESS_DENIED("CON003", "Access is denied"),

    // Validation errors
    INVALID_INPUT_VALUE("CON100", "Invalid input value"),
    INVALID_TYPE_VALUE("CON101", "Invalid type value"),
    VALIDATION_FAILED("CON102", "Validation failed"),

    // Internal errors
    INTERNAL_SERVER_ERROR("CON500", "Internal server error"),
    UNEXPECTED_ERROR("CON999", "Unexpected error"),
    INVALID_MESSAGE_STATUS("CON1000", "Invalid message status")
    ;

    private final String code;
    private final String message;

}
