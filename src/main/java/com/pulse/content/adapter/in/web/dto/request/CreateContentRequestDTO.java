package com.pulse.content.adapter.in.web.dto.request;

import com.pulse.content.adapter.in.web.dto.FileDTO;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.MemberId;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContentRequestDTO {

    private MemberId memberId;                  // 작성자 id
    private List<String> hashTagNames;          // hashTag 목록
    private List<FileDTO> files;               // 첨부 파일 목록
    private String title;                       // 게시글 제목
    private String text;                        // 게시글 내용
    private ContentVisibility contentVisibility;      // 공개 범위
}
