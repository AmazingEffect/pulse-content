package com.pulse.content.mapper.helper;

import com.pulse.content.adapter.in.web.dto.FileDTO;
import com.pulse.content.adapter.out.persistence.entity.ContentEntity;
import com.pulse.content.adapter.out.persistence.entity.vo.ContentAttachmentEntity;
import com.pulse.content.domain.Content;
import com.pulse.content.domain.vo.ContentAttachment;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.mapper.ContentAttachmentMapper;
import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentMapperHelper {

    private final ContentMapper contentMapper;
    private final ContentAttachmentMapper contentAttachmentMapper;


    /**
     * 콘텐츠 도메인 객체를 콘텐츠 엔티티 객체로 변환
     * @param content - 콘텐츠 도메인
     * @return 콘텐츠 엔티티
     */
    @Named("contentDomainToEntity")
    public ContentEntity contentDomainToEntity(Content content) {
        return contentMapper.domainToEntity(content);
    }


    /**
     * 첨부 파일 도메인 객체를 엔티티 객체로 변환
     * @param contentAttachments - 첨부 파일 도메인 목록
     * @return 첨부 파일 엔티티 목록
     */
    @Named("contentAttachmentsDomainToEntity")
    public List<ContentAttachmentEntity> contentAttachmentsDomainToEntity(List<ContentAttachment> contentAttachments) {
        return contentAttachments.stream()
                .map(contentAttachmentMapper::domainToEntity)
                .collect(Collectors.toList());
    }

    /**
     * 콘텐츠 엔티티 객체를 콘텐츠 도메인 객체로 변환
     * @param contentEntity - 콘텐츠 엔티티
     * @return 콘텐츠 도메인
     */
    @Named("contentEntityToDomain")
    public Content contentEntityToDomain(ContentEntity contentEntity) {
        return contentMapper.entityToDomain(contentEntity);
    }

    /**
     * 첨부 파일 엔티티 객체를 도메인 객체로 변환
     * @param contentAttachmentEntities - 첨부 파일  엔티티 목록
     * @return 첨부 파일 도메인 목록
     */
    @Named("contentAttachmentsEntityToDomain")
    public List<ContentAttachment> contentAttachmentsEntityToDomain(List<ContentAttachmentEntity> contentAttachmentEntities) {
        return contentAttachmentEntities.stream()
                .map(contentAttachmentMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    /**
     * 콘텐츠 상세(title, text) 도메인 객체로 변환
     * @param title - 콘텐츠 제목
     * @param text - 콘텐츠 상세 내용
     * @return 콘텐츠 상세(title, text) 도메인 객체
     */
    @Named("contentDetailEntityToDomain")
    public ContentDetail contentDetailEntityToDomain(String title, String text) {
        return ContentDetail.of(title, text);
    }

    @Named("contentAttachmentsDtoToDomain")
    public List<ContentAttachment> contentAttachmentsDtoToDomain(List<FileDTO> files) {
        return files.stream()
                .map(fileDTO -> {
                    return ContentAttachment.of(
                            fileDTO.getAttachId(), fileDTO.getUrl(), fileDTO.getFileId(),
                            fileDTO.getContentType(), fileDTO.getSize(), fileDTO.getAttachmentType()
                    );
                })
                .collect(Collectors.toList());
    }
}
