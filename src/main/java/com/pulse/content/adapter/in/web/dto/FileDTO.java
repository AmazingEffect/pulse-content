package com.pulse.content.adapter.in.web.dto;

import com.pulse.content.common.enumerate.AttachmentType;
import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDTO {

    @Valid
    @NotNull
    private AttachId attachId;
    @NotBlank(message = "파일 URL은 필수입니다.")
    private String url;
    @Valid
    @NotNull
    private FileId fileId;
    @NotBlank(message = "MIME은 필수입니다.")
    private String contentType;             // MIME
    @NotNull(message = "파일 크기는 null일 수 없습니다.")
    @Positive(message = "파일 크기는 0보다 커야 합니다.")
    private Long size;
    @NotNull(message = "첨부 파일 분류는 필수입니다.")
    private AttachmentType attachmentType;  // 첨부 파일 분류
}
