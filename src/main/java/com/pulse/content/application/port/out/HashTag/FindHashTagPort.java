package com.pulse.content.application.port.out.HashTag;

import com.pulse.content.domain.HashTag;

import java.util.List;

public interface FindHashTagPort {
    HashTag findByName(String name);
    List<HashTag> findByNames(List<String> names);
}
