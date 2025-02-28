package com.pulse.content.domain.vo;

import com.pulse.content.common.enumerate.AttachmentType;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAttachment {

    private AttachId attachId;
    private String url;
    private FileId fileId;
    private String contentType;             // MIME
    private Long size;
    private AttachmentType attachmentType;  // 첨부 파일 분류


    // factory method
    public static PostAttachment of(
            AttachId attachId, String url, FileId fileId, String contentType,
            Long size, AttachmentType attachmentType
    ) {
        return PostAttachment.builder()
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .contentType(contentType)
                .size(size)
                .attachmentType(attachmentType)
                .build();
    }
}
