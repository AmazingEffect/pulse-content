package com.pulse.content.adapter.in.web.dto.request;

import com.pulse.content.adapter.in.web.dto.FileDTO;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.MemberId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContentRequestDTO {

    @Valid
    @NotNull
    private MemberId memberId;                  // 작성자 id
    private List<String> hashTagNames;          // hashTag 목록
    @Valid
    @Size(min = 1, message = "첨부 파일은 최소 1개 이상이어야 합니다.")
    private List<@Valid FileDTO> files;               // 첨부 파일 목록
    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;                       // 게시글 제목
    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String text;                        // 게시글 내용
    @NotNull(message = "공개 범위를 설정해주세요.")
    private ContentVisibility contentVisibility;      // 공개 범위
}
