package com.pulse.content.domain;

import com.pulse.content.domain.key.MemberId;
import com.pulse.content.exception.ContentException;
import com.pulse.content.exception.ErrorCode;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId memberId;
    private String name;
    private String nickName;
    private String profilePictureUrl;

    // factory method
    public static Member of(MemberId memberId, String name, String nickName, String profilePictureUrl) {
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .nickName(nickName)
                .profilePictureUrl(profilePictureUrl)
                .build();
    }

    /**
     * 콘텐츠 작성자 id 유효성 검사
     * 콘텐츠 작성자 Id 와 요청 회원 Id가 같은지 확인
     * @param writerMemberId 콘텐츠 작성자 id
     * @param memberId 수정 및 삭제 요청 회원 id
     */
    public static void writerIdValidation(MemberId writerMemberId, MemberId memberId) {
        if (!writerMemberId.equals(memberId)) {
            throw new ContentException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }
}
