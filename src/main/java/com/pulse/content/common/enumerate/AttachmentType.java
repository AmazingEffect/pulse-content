package com.pulse.content.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttachmentType {

    IMAGE("IMAGE", "IMAGE"),
    VIDEO("VIDEO", "VIDEO"),
    AUDIO("AUDIO", "AUDIO"),
    OTHER("OTHER", "OTHER");

    private final String code;
    private final String description;
}
