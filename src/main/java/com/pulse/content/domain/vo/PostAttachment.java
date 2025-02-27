package com.pulse.content.domain.vo;

import com.pulse.content.domain.key.AttachId;
import com.pulse.content.domain.key.FileId;
import lombok.*;

import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAttachment {

    private AttachId attachId;
    private String url;
    private FileId fileId;


    // factory method
    public static PostAttachment of(AttachId attachId, String url, FileId fileId) {
        return PostAttachment.builder()
                .attachId(attachId)
                .url(url)
                .fileId(fileId)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostAttachment that = (PostAttachment) o;
        return Objects.equals(attachId, that.attachId) && Objects.equals(url, that.url) && Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachId, url, fileId);
    }
}
