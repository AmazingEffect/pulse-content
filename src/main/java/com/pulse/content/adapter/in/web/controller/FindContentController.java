package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.response.ContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.JwtResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.service.FindContentService;
import com.pulse.content.domain.key.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find/content")
@RequiredArgsConstructor
public class FindContentController {

    private final FindContentService findContentService;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<ContentResponseDTO>> findContent(@PathVariable PostId postId) {
        ContentResponseDTO content = findContentService.findContent(postId);
        return ResponseEntity.ok(ApiResponse.success(content));
    }
}
