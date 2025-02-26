package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.port.in.outbox.CreateContentsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateContentController {
    private final CreateContentsUseCase createContentsUseCase;

    @PostMapping("/v1/create")
    public ResponseEntity<ApiResponse<CreateContentResponseDTO>> createContent(@RequestBody CreateContentRequestDTO createContentRequestDTO) {
        CreateContentResponseDTO createContentResponseDTO = createContentsUseCase.create(createContentRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(createContentResponseDTO));
    }
}
