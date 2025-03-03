package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateContentController {
    private final CreateContentsUseCase createContentsUseCase;

    /**
     * @apiNote 게시글 등록을 위한 api
     * @param createContentRequestDTO 게시글 데이터
     * @return 응답 상태
     */
    @PostMapping("/v1/create")
    public ResponseEntity<ApiResponse<CreateContentResponseDTO>> createContent(@RequestBody CreateContentRequestDTO createContentRequestDTO) {
        CreateContentResponseDTO createContentResponseDTO = createContentsUseCase.create(createContentRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(createContentResponseDTO));
    }
}
