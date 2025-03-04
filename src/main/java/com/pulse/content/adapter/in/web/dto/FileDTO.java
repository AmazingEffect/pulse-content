package com.pulse.content.adapter.in.web.dto;

import com.pulse.content.common.enumerate.AttachmentType;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDTO {
    private AttachId attachId;
    private String url;
    private FileId fileId;
    private String contentType;             // MIME
    private Long size;
    private AttachmentType attachmentType;  // 첨부 파일 분류
}
