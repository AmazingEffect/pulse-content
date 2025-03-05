package com.pulse.content.application.port.out.HashTag;

import com.pulse.content.domain.HashTag;

import java.util.List;

public interface CreateHashTagPort {
    HashTag create(HashTag hashTag);
    List<HashTag> createAll(List<HashTag> hashTags);
}
