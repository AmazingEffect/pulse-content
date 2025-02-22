package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.domain.key.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find/content")
@RequiredArgsConstructor
public class FindContentController {

    private final FindContentUseCase findContentUseCase;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<FindContentResponseDTO>> findContent(@PathVariable PostId postId) {
        FindContentResponseDTO content = findContentUseCase.findContent(postId);
        return ResponseEntity.ok(ApiResponse.success(content));
    }
}
