package com.pulse.content.application.port.out.map;

import java.util.List;

public interface DeleteContentHashTagMapPort {
    void deleteAll(List<Long> contentHashTagMapIds);
}
