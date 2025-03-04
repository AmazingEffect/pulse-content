package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.domain.key.ContentId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ContentController {

    private final CreateContentsUseCase createContentsUseCase;
    private final FindContentUseCase findContentUseCase;

    @GetMapping("/find/content/{contentId}")
    public ResponseEntity<ApiResponse<FindContentResponseDTO>> findContent(@PathVariable ContentId contentId) {
        FindContentResponseDTO content = findContentUseCase.findContent(contentId);
        return ResponseEntity.ok(ApiResponse.success(content));
    }

    /**
     * @apiNote 게시글 등록을 위한 api
     * @param createContentRequestDTO 게시글 데이터
     * @return 응답 상태
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateContentResponseDTO>> createContent(@RequestBody CreateContentRequestDTO createContentRequestDTO) {
        CreateContentResponseDTO createContentResponseDTO = createContentsUseCase.create(createContentRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(createContentResponseDTO));
    }
}
