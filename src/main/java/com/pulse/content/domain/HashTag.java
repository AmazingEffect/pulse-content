package com.pulse.content.domain;

import com.pulse.content.domain.key.HasTagId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTag {

    private HasTagId id;

    private String name;    // 해시태그 이름

    // factory method
    public static HashTag of(HasTagId id, String name) {
        return HashTag.builder()
                .id(id)
                .name(name)
                .build();
    }
}
