package com.pulse.content.application.port.in.hashTag;

import com.pulse.content.domain.HashTag;

public interface FindHashTagUseCase {
    HashTag findByName(String name);
}
