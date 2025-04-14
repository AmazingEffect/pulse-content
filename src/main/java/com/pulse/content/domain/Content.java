package com.pulse.content.domain;

import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.domain.map.ContentHashTagMap;
import com.pulse.content.domain.vo.ContentAttachment;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    private ContentId contentId;
    private MemberId writerId;
    private List<ContentHashTagMap> contentHashTagMaps;
    private List<ContentCategoryMap> contentCategories;
    private List<ContentAttachment> contentAttachments;
    private ContentDetail contentDetail;
    private ContentStatus contentStatus;
    private ContentVisibility contentVisibility;

    // factory method
    public static Content of(ContentId contentId, MemberId writerId,  List<ContentHashTagMap> contentHashTagMaps, List<ContentCategoryMap> contentCategories, ContentDetail contentDetail, ContentStatus contentStatus, ContentVisibility contentVisibility) {
        return Content.builder()
                .contentId(contentId)
                .writerId(writerId)
                .contentHashTagMaps(contentHashTagMaps)
                .contentCategories(contentCategories)
                .contentDetail(contentDetail)
                .contentStatus(contentStatus)
                .contentVisibility(contentVisibility)
                .build();
    }

    /**
     * ContentStatus(게시글 상태) 저장
     * @param contentStatus 저장할 게시글 상태
     */
    public void putContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    /**
     * ContentHashTagMaps 저장
     * @param contentHashTagMaps contentHashTagMaps
     */
    public void putContentHasTagMap(List<ContentHashTagMap> contentHashTagMaps) {
        this.contentHashTagMaps = contentHashTagMaps;
    }

    /**
     * 콘텐츠 제목 및 내용 저장
     * @param title 제목
     * @param contentText 내용
     */
    public void putContentDetail(String title, String contentText) {
        if (ObjectUtils.isEmpty(title) || ObjectUtils.isEmpty(contentText)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }

        ContentDetail updateContentDetail = ContentDetail.of(title, contentText);

        if (ObjectUtils.isEmpty(contentDetail)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
        this.contentDetail = updateContentDetail;
    }

    /**
     * 콘텐츠 공개 범위 변경
     * @param contentVisibility 저장할 콘텐츠 공개 범위
     */
    public void putContentVisibility(ContentVisibility contentVisibility) {
        if (ObjectUtils.isEmpty(contentVisibility)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
        this.contentVisibility = contentVisibility;
    }

    /**
     * 작성자 id와 memberId 비교
     * @param memberId 비교할 memberId
     */
    public void writerIdValidate(MemberId memberId) {
        if (!this.writerId.equals(memberId)) {
            throw new ContentException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }

    /**
     * 해시태그 아이디 목록 추출
     * @return 해시태그 아이디 목록
     */
    public List<Long> filterHashTagIds() {
         return this.contentHashTagMaps.stream()
                .map(contentHashTagMap -> contentHashTagMap.getHashTag().getHashTagId().id())
                .toList();
    }
}

