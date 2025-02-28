package com.pulse.content.mapper.helper;

import com.pulse.content.adapter.out.persistence.entity.vo.PostAttachmentEntity;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.domain.vo.PostAttachment;
import com.pulse.content.mapper.PostAttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentMapperHelper {

    private final PostAttachmentMapper postAttachmentMapper;

    /**
     * 첨부 파일 도메인 객체를 엔티티 객체로 변환
     * @param postAttachments - 첨부 파일 도메인 목록
     * @return 첨부 파일 엔티티 목록
     */
    @Named("postAttachmentsDomainToEntity")
    public List<PostAttachmentEntity> postAttachmentsDomainToEntity(List<PostAttachment> postAttachments) {
        return postAttachments.stream()
                .map(postAttachmentMapper::domainToEntity)
                .collect(Collectors.toList());
    }

    /**
     * 첨부 파일 엔티티 객체를 도메인 객체로 변환
     * @param postAttachmentEntities - 첨부 파일  엔티티 목록
     * @return 첨부 파일 도메인 목록
     */
    @Named("postAttachmentsEntityToDomain")
    public List<PostAttachment> postAttachmentsEntityToDomain(List<PostAttachmentEntity> postAttachmentEntities) {
        return postAttachmentEntities.stream()
                .map(postAttachmentMapper::entityToDomain)
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
}
