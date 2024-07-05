package com.pulse.content.service.grpc;

import com.pulse.content.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
//@GrpcService
public class ContentServiceGrpcImpl {

    private final ContentService contentService;
    private final ContentMapper memberMapper;


}
