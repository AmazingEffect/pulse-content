package com.pulse.content.application.port.out.HashTag;

import java.util.List;

public interface DeleteHashTagPort {
    void deleteAllById(List<Long> ids);
}
