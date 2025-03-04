package com.pulse.content.application.port.out.HashTag;

import com.pulse.content.domain.HashTag;

public interface FindHashTagPort {
    HashTag findByName(String name);
}
