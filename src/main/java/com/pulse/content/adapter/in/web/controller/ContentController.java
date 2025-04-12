package com.pulse.content.adapter.in.web.controller;

import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.request.DeleteContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.request.UpdateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.DeleteContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.api.ApiResponse;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.DeleteContentUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.application.port.in.content.UpdateContentUseCase;
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
    private final UpdateContentUseCase updateContentUseCase;
    private final DeleteContentUseCase deleteContentUseCase;

    /**
     * @apiNote 게시글 단건 조회
     * @param contentId
     * @return
     */
    @GetMapping("/{contentId}")
    public ApiResponse<FindContentResponseDTO> findContent(@PathVariable ContentId contentId) {
        FindContentResponseDTO content = findContentUseCase.findContent(contentId);
        return ApiResponse.success(content);
    }

    /**
     * @apiNote 게시글 등록을 위한 api
     * @param createContentRequestDTO 게시글 데이터
     * @return 응답 상태
     */
    @PostMapping("/create")
    public ApiResponse<CreateContentResponseDTO> createContent(
            @RequestBody CreateContentRequestDTO createContentRequestDTO
    ) {
        CreateContentResponseDTO createContentResponseDTO = createContentsUseCase.create(createContentRequestDTO);
        return ApiResponse.success(createContentResponseDTO);
    }

    /**
     * @apiNote 게시글 수정을 위한 api
     * @param updateContentRequestDTO 게시글 데이터
     * @return 응답 상태
     */
    @PostMapping("/create")
    public ApiResponse<UpdateContentResponseDTO> createContent(
            @RequestBody UpdateContentRequestDTO updateContentRequestDTO
    ) {
        UpdateContentResponseDTO updateContentResponseDTO = updateContentUseCase.update(updateContentRequestDTO);
        return ApiResponse.success(updateContentResponseDTO);
    }

    /**
     * @apiNote 게시글 삭제를 위한 api
     * @param deleteContentRequestDTO 게시글 데이터
     * @return 응답 상태
     */
    @DeleteMapping("/delete")
    public ApiResponse<DeleteContentResponseDTO> deleteContent(
            @RequestBody DeleteContentRequestDTO deleteContentRequestDTO
    ) {
        DeleteContentResponseDTO deleteContentResponseDTO = deleteContentUseCase.delete(deleteContentRequestDTO);
        return ApiResponse.success(deleteContentResponseDTO);
    }
}
