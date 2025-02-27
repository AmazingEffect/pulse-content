package com.pulse.content.domain;

import com.pulse.content.domain.key.HasTagId;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTag {

    private HasTagId hasTagId;
    private String name;    // 해시태그 이름

    // factory method
    public static HashTag of(String name) {
        return HashTag.builder()
                .name(name)
                .build();
    }
}
