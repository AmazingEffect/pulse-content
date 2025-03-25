package com.pulse.content.application.port.out.attachment;

import com.pulse.content.domain.vo.ContentAttachment;

import java.util.List;

public interface CreateContentAttachmentPort {
    List<ContentAttachment> createAll(List<ContentAttachment> contentAttachments);
}
