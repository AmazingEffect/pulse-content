package com.pulse.content.application.service.map;

import com.pulse.content.application.port.FindPostHashTagMapPort;
import com.pulse.content.application.port.in.map.FindPostHashTagMapUseCase;
import com.pulse.content.common.annotation.UseCase;
import com.pulse.content.domain.key.PostId;
import com.pulse.content.domain.map.PostHashTag;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostHashTagMapService implements FindPostHashTagMapUseCase {

    private final FindPostHashTagMapPort findPostHashTagMapPort;

    @Override
    public List<PostHashTag> findHashTagsByPostId(PostId postId) {
        return findPostHashTagMapPort.findHashTagsByPostId(postId);
    }
}
