package com.pulse.content.service.grpc;

import com.pulse.content.mapper.ContentMapper;
import com.pulse.content.service.usecase.ContentService;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@RequiredArgsConstructor
//@GrpcService
public class ContentServiceGrpcImpl {

    private final ContentService contentService;
    private final ContentMapper memberMapper;


}
