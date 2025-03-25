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

    CONTENT_ID_REQUIRED("CON200", "Content ID is required"),
    CONTENT_PERMISSION_DENIED("CON202", "You do not have permission to modify this content"),
    CONTENT_TITLE_REQUIRED("CON203", "Content title is required"),
    CONTENT_TEXT_REQUIRED("CON204", "Content text is required"),
    CONTENT_VISIBILITY_REQUIRED("CON205", "Content visibility is required"),

    // Internal errors
    INTERNAL_SERVER_ERROR("CON500", "Internal server error"),
    UNEXPECTED_ERROR("CON999", "Unexpected error"),
    INVALID_MESSAGE_STATUS("CON1000", "Invalid message status"),
    OUTBOX_STATUS_NOT_FOUND("","" );

    private final String code;
    private final String message;

}
