package com.pulse.content.domain;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 댓글 관련 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comment")
public class Comment {

    private Long id;
    private Post post;
    private Long memberId;
    private String content;

}
