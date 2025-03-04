package com.pulse.content.domain;

import com.pulse.content.common.enumerate.ContentStatus;
import com.pulse.content.common.enumerate.ContentVisibility;
import com.pulse.content.domain.key.MemberId;
import com.pulse.content.domain.key.ContentId;
import com.pulse.content.domain.map.ContentCategoryMap;
import com.pulse.content.domain.vo.ContentDetail;
import com.pulse.content.domain.vo.ContentAttachment;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    private ContentId contentId;
    private MemberId memberId;
    private List<Long> hashTagIds;
    private List<ContentCategoryMap> contentCategories;
    private List<ContentAttachment> contentAttachments;
    private ContentDetail contentDetail;
    private ContentStatus contentStatus;
    private ContentVisibility contentVisibility;

    // factory method
    public static Content of(ContentId contentId, MemberId memberId, List<Long> hashTagIds, List<ContentCategoryMap> contentCategories, List<ContentAttachment> contentAttachments, ContentDetail contentDetail, ContentStatus contentStatus, ContentVisibility contentVisibility) {
        return Content.builder()
                .contentId(contentId)
                .memberId(memberId)
                .hashTagIds(hashTagIds)
                .contentCategories(contentCategories)
                .contentAttachments(contentAttachments)
                .contentDetail(contentDetail)
                .contentStatus(contentStatus)
                .contentVisibility(contentVisibility)
                .build();
    }

    /**
     * ContentStatus(게시글 상태) 변경
     * @param contentStatus - 변경할 게시글 상태
     */
    public void changeContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }
}

