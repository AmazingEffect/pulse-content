package com.pulse.content.domain;

import com.pulse.content.domain.key.HasTagId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTag {

    private HasTagId id;

    private String name;    // 해시태그 이름

}
