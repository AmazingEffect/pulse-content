package com.pulse.content.application.service;

import com.pulse.content.adapter.in.web.dto.FileDTO;
import com.pulse.content.adapter.in.web.dto.request.CreateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.request.DeleteContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.request.UpdateContentRequestDTO;
import com.pulse.content.adapter.in.web.dto.response.CreateContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.DeleteContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.FindContentResponseDTO;
import com.pulse.content.adapter.in.web.dto.response.UpdateContentResponseDTO;
import com.pulse.content.application.port.in.content.CreateContentsUseCase;
import com.pulse.content.application.port.in.content.DeleteContentUseCase;
import com.pulse.content.application.port.in.content.FindContentUseCase;
import com.pulse.content.application.port.in.content.UpdateContentUseCase;
import com.pulse.content.application.port.out.HashTag.CreateHashTagPort;
import com.pulse.content.application.port.out.HashTag.DeleteHashTagPort;
import com.pulse.content.application.port.out.HashTag.FindHashTagPort;
import com.pulse.content.application.port.out.attachment.CreateContentAttachmentPort;
import com.pulse.content.application.port.out.attachment.DeleteContentAttachmentPort;
import com.pulse.content.application.port.out.content.CreateContentPort;
import com.pulse.content.application.port.out.content.DeleteContentPort;
import com.pulse.content.application.port.out.content.FindContentPort;
import com.pulse.content.application.port.out.content.UpdateContentPort;
import com.pulse.content.application.port.out.map.CreateContentHashTagMapPort;
import com.pulse.content.application.port.out.map.DeleteContentHashTagMapPort;
import com.pulse.content.application.port.out.map.FindContentHashTagMapPort;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.common.enumerate.AttachmentType;
import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.HashTag;
import com.pulse.content.domain.Member;
import com.pulse.content.domain.key.*;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.domain.vo.ContentAttachment;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentService implements CreateContentsUseCase, FindContentUseCase, UpdateContentUseCase, DeleteContentUseCase {

    private final ContentMapper contentMapper;

    private final CreateContentPort createContentPort;
    private final CreateHashTagPort createHashTagPort;
    private final CreateContentHashTagMapPort createContentHashTagMapPort;
    private final CreateContentAttachmentPort createContentAttachmentPort;

    private final FindContentPort findContentPort;
    private final FindHashTagPort findHashTagPort;
    private final FindContentHashTagMapPort findContentHashTagMapPort;

    private final UpdateContentPort updateContentPort;

    private final DeleteContentAttachmentPort deleteContentAttachmentPort;
    private final DeleteContentPort deleteContentPort;
    private final DeleteHashTagPort deleteHashTagPort;
    private final DeleteContentHashTagMapPort deleteContentHashTagMapPort;

    /**
     * @apiNote 게시글 작성 및 관련 데이터 저장
     * -> hashtag, hashtag-map, category-map
     * @param createContentRequestDto - 저장할 콘텐츠 정보
     * @return 저장된 콘텐츠 정보
     */
    @Transactional
    @Override
    public CreateContentResponseDTO create(CreateContentRequestDTO createContentRequestDto) {
        // todo: 게시글
        Content content = contentMapper.createRequestDtoToDomain(createContentRequestDto);
        // 게시글 상태(ContentStatus) 세팅
        content.putContentStatus(ContentStatus.PUBLISHED);
        // 게시글 저장
        Content createdContent = createContentPort.create(content);

        // file 리스트 저장
        List<FileDTO> files = createContentRequestDto.getFiles();
        createContentAttachments(files);

        // 해시태그 목록 저장
        List<String> hashTagNames = createContentRequestDto.getHashTagNames();
        List<HashTag> existingHashTags = createHashTags(hashTagNames);

        // 해시태그 맵 목록 저장
        createContentHashTagMaps(existingHashTags, createdContent);

        return contentMapper.domainToCreateResponseDTO(createdContent);
    }

    /**
     * 게시글 수정 및 관련 데이터 수정
     * 1. 콘텐츠 수정
     * 2. file 수정
     * 3. 해시태그 및 해시태그 맵 수정
     * @param updateContentRequestDTO 수정할 콘텐츠 정보
     * @return 수정된 콘텐츠 정보
     */
    @Transactional
    @Override
    public UpdateContentResponseDTO update(UpdateContentRequestDTO updateContentRequestDTO) {
        ContentId contentId = updateContentRequestDTO.getContentId();
        Content.contentIdValidation(contentId);

        // 콘텐츠 조회
        Content content = findContentPort.findContent(contentId);
        Content.contentValidation(content);

        // 작성자와 수정자 아이디 비교
        MemberId writerMemberId = content.getMemberId();
        MemberId updateMemberId = updateContentRequestDTO.getMemberId();
        Member.writerIdValidation(writerMemberId, updateMemberId);

        // 콘텐츠 제목 및 내용 변경
        String title = updateContentRequestDTO.getTitle();
        String contentText = updateContentRequestDTO.getText();
        Content.titleAndTextValidation(title, contentText);
        content.putContentDetail(ContentDetail.of(title, contentText));

        // 콘텐츠 공개 범위 변경
        ContentVisibility contentVisibility = updateContentRequestDTO.getContentVisibility();
        ContentVisibility.contentVisibilityValidation(contentVisibility);
        content.putContentVisibility(contentVisibility);

        // 콘텐츠 수정
        Content updatedContent = updateContentPort.update(content);

        // todo: file 리스트 수정

        // 해시태그 및 해시태그 맵
        List<String> hashTagNames = updateContentRequestDTO.getHashTagNames();
        List<ContentHashTagMap> contentHashTagMaps = content.getContentHashTagMaps();
        ContentHashTagMap.contentHashTagMapValidation(contentHashTagMaps);

        // 해시태그 맵 삭제
        deleteAllContentHashTagMap(contentHashTagMaps, hashTagNames);

        // 수정한 해시태그 목록 중 신규 해시태그 등록
        createContentHashTagMap(contentHashTagMaps, hashTagNames, updatedContent);

        return contentMapper.domainToUpdateResponseDTO(updatedContent);
    }

    /**
     * 게시글 삭제 및 관련 데이터 삭제
     * 1. 해시태그 및 해시태그 맵 삭제
     * 2. file 삭제
     * 3. 콘텐츠 삭제
     * @param deleteContentRequestDTO 삭제할 콘텐츠 정보
     * @return 삭제된 콘텐츠 정보
     */
    @Override
    public DeleteContentResponseDTO delete(DeleteContentRequestDTO deleteContentRequestDTO) {
        // 콘텐츠 아이디 유효성 검사
        ContentId contentId = deleteContentRequestDTO.getContentId();
        Content.contentIdValidation(contentId);

        // 콘텐츠 유효성 검사
        Content content = findContentPort.findContent(contentId);
        Content.contentValidation(content);

        // 작성자 아이디 유효성 검사
        MemberId writerMemberId = content.getMemberId();
        MemberId deleterMemberId = deleteContentRequestDTO.getMemberId();
        Member.writerIdValidation(writerMemberId, deleterMemberId);

        // 1. 해시태그 및 해시태그 맵 삭제
        List<ContentHashTagMap> contentHashTagMaps = content.getContentHashTagMaps();
        List<Long> hashTagIds = contentHashTagMaps.stream()
                        .map(contentHashTagMap -> contentHashTagMap.getHashTag().getHashTagId().id())
                        .toList();
        deleteHashTagPort.deleteAllById(hashTagIds);
        deleteContentHashTagMapPort.deleteAllByContentId(contentId.id());

        // file 삭제
        deleteContentAttachmentPort.deleteAllByContentId(contentId.id());

        // 콘텐츠 삭제
        deleteContentPort.deleteById(contentId.id());

        return contentMapper.domainToDeleteResponseDTO(content);
    }

    /**
     * 첨부 파일 리스트 저장
     * @param files - 저장할 첨부 파일 리스트
     * @return 저장된 첨부 파일 리스트
     */
    private List<ContentAttachment> createContentAttachments(List<FileDTO> files) {
        List<ContentAttachment> contentAttachments = files.stream()
                .map(file -> {
                    AttachId attachId = file.getAttachId();
                    String url = file.getUrl();
                    FileId fileId = file.getFileId();
                    String contentType = file.getContentType();
                    Long size = file.getSize();
                    AttachmentType attachmentType = file.getAttachmentType();

                    return ContentAttachment.of(attachId, url, fileId, contentType, size, attachmentType);
                })
                .toList();
        return createContentAttachmentPort.createAll(contentAttachments);
    }

    /**
     * @apiNote 해시태그명으로 해시태그 조회 후, 저장 되어 있지 않은 해시태그명만 저장
     * @param hashTagNames - 유저가 입력한 해시태그명
     * @return 저장된 해시태그 목록
     */
    private List<HashTag> createHashTags(List<String> hashTagNames) {
        // 이미 저장된 해시태그 목록
        List<HashTag> existingHashTags = findHashTagPort.findByNames(hashTagNames);
        // 새로 저장할 해시태그 목록(저장 되어 있지 않은 해시태그명)
        List<HashTag> newHashTags = hashTagNames.stream()
                .filter(name -> existingHashTags.stream().noneMatch(hashTag -> hashTag.getName().equals(name)))
                .map(HashTag::of).collect(Collectors.toList());
        // 해시태그 목록 저장
        List<HashTag> createdHashTags = createHashTagPort.createAll(newHashTags);
        existingHashTags.addAll(createdHashTags);
        return existingHashTags;
    }

    /**
     * @apiNote 해시태그와 콘텐츠로 ContentHashTagMap 저장
     * @param existingHashTags - 기존에 저장 되어 있는 해시태그
     * @param createdContent - 저장된 게시글
     * @return 저장된 해시태그 맵 목록
     */
    private List<ContentHashTagMap> createContentHashTagMaps(List<HashTag> existingHashTags, Content createdContent) {
        List<ContentHashTagMap> contentHashTagMaps = existingHashTags.stream()
                .map(hashTag -> ContentHashTagMap.of(createdContent, hashTag))
                .toList();
        return createContentHashTagMapPort.createAll(contentHashTagMaps);
    }

    /**
     * @apiNote ContentHashTagMap 리스트 삭제
     * @param contentHashTagMaps - 저장 되어 있는 ContentHashTagMap 리스트
     * @param hashTagNames - 유저가 입력 하지 않은 해스태그 리스트
     */
    private void deleteAllContentHashTagMap(List<ContentHashTagMap> contentHashTagMaps, List<String> hashTagNames) {
        List<Long> deleteContentHashTagMap = contentHashTagMaps.stream()
                .filter(contentHashTagMap -> !hashTagNames.contains(contentHashTagMap.getHashTag().getName()))
                .map(contentHashTagMap -> contentHashTagMap.getContentHashTagMapId().id())
                .toList();
        // 콘텐츠 해시태그 맵 삭제
        deleteContentHashTagMapPort.deleteAllById(deleteContentHashTagMap);
    }

    /**
     * 신규 콘텐츠 해시태그 맵 저장
     * @param contentHashTagMaps 콘텐츠 해시태그 맵 목록
     * @param hashTagNames 해시태그명 목록
     * @param content 콘텐츠
     * @return 저장된 해시태그 맵 목록
     */
    private List<ContentHashTagMap> createContentHashTagMap(List<ContentHashTagMap> contentHashTagMaps, List<String> hashTagNames, Content content) {
        Set<String> existingNames = contentHashTagMaps.stream()
                .map(contentHashTagMap -> contentHashTagMap.getHashTag().getName())
                .collect(Collectors.toSet());

        // 새로운 해시태그 저장
        Set<String> newHashTags = new HashSet<>(hashTagNames);
        newHashTags.removeAll(existingNames); // existingNames에 있는 것 제거 -> 남는 것은 추가 해야 하는 것
        List<HashTag> existingHashTags = createHashTags(new ArrayList<>(existingNames));  // 해시태그 저장
        return createContentHashTagMaps(existingHashTags, content);    // 해시태그 맵 저장
    }

    @Override
    public FindContentResponseDTO findContent(ContentId contentId) {
        Content findContent = findContentPort.findContent(contentId); // 행위에 대한 이름으로
        return contentMapper.domainToResponseDTO(findContent);
    }
}
