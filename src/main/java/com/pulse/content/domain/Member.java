package com.pulse.content.domain;

import com.pulse.content.domain.key.MemberId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId memberId;
    private String name;
    private String nickName;
    private String profilePictureUrl;

    // factory method
    public static Member of(MemberId memberId, String name, String nickName, String profilePictureUrl) {
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .nickName(nickName)
                .profilePictureUrl(profilePictureUrl)
                .build();
    }
}
