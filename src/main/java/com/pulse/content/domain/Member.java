package com.pulse.content.domain;

public record Member(
        MemberId memberId,
        String name,
        String nickName,
        String profilePictureUrl
) {}
