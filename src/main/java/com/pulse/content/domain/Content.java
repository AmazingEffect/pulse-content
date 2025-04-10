package com.pulse.content.domain;

import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.domain.map.ContentHashTagMap;
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
    private MemberId memberId;
    private List<ContentHashTagMap> contentHashTagMaps;
    private List<ContentCategoryMap> contentCategories;
    private ContentDetail contentDetail;
    private ContentStatus contentStatus;
    private ContentVisibility contentVisibility;

    // factory method
    public static Content of(ContentId contentId, MemberId memberId,  List<ContentHashTagMap> contentHashTagMaps, List<ContentCategoryMap> contentCategories, ContentDetail contentDetail, ContentStatus contentStatus, ContentVisibility contentVisibility) {
        return Content.builder()
                .contentId(contentId)
                .memberId(memberId)
                .contentHashTagMaps(contentHashTagMaps)
                .contentCategories(contentCategories)
                .contentDetail(contentDetail)
                .contentStatus(contentStatus)
                .contentVisibility(contentVisibility)
                .build();
    }

    /**
     * 콘텐츠 유효성 검사
     * @param content 콘텐츠
     */
    public static void contentValidation(Content content) {
        if (ObjectUtils.isEmpty(content)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
    }

    /**
     * 콘텐츠 Id 유효성 검사
     * @param contentId 콘텐츠 id
     */
    public static void contentIdValidation(ContentId contentId) {
        if (ObjectUtils.isEmpty(contentId) || ObjectUtils.isEmpty(contentId.id())) {
            throw new ContentException(ErrorCode.CONTENT_ID_REQUIRED);
        }
    }

    /**
     * 콘텐츠 제목 및 내용 유효성 검사
     * @param title 제목
     * @param contentText 내용
     */
    public static void titleAndTextValidation(String title, String contentText) {
        if (ObjectUtils.isEmpty(title) || ObjectUtils.isEmpty(contentText)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
    }

    /**
     * ContentStatus(게시글 상태) 변경
     * @param contentStatus - 변경할 게시글 상태
     */
    public void changeContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    /**
     * 콘텐츠 제목 및 내용 변경
     * @param contentDetail - 변경할 콘텐츠 제목 및 내용
     */
    public void changeContentDetail(ContentDetail contentDetail) {
        if (ObjectUtils.isEmpty(contentDetail)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
        this.contentDetail = contentDetail;
    }

    /**
     * 콘텐츠 공개 범위 변경
     * @param contentVisibility - 변경할 콘텐츠 공개 범위
     */
    public void changeContentVisibility(ContentVisibility contentVisibility) {
        if (ObjectUtils.isEmpty(contentVisibility)) {
            throw new ContentException(ErrorCode.ENTITY_NOT_FOUND);
        }
        this.contentVisibility = contentVisibility;
    }
}

