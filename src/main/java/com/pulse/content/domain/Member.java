package com.pulse.content.domain;

import com.pulse.content.domain.key.MemberId;

public record Member(
        MemberId memberId,
        String name,
        String nickName,
        String profilePictureUrl
) {
}
