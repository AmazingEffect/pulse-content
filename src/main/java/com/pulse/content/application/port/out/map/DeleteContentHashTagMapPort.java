package com.pulse.content.application.port.out.map;

import com.pulse.content.domain.key.ContentId;

import java.util.List;

public interface DeleteContentHashTagMapPort {
    void deleteAllById(List<Long> contentHashTagMapIds);

    void deleteAllByContentId(Long contentId);
}
