package com.saas.projectDelivery.service;


import com.saas.projectDelivery.event.ReleaseResourceEventProducer;
import com.saas.projectDelivery.mapper.ReleaseResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReleaseResourceService {

    private final ReleaseResourceMapper mapper;
    private final ReleaseResourceEventProducer eventProducer;

    public String releaseResources(UUID tenantId, UUID projectId) {
        var eventDto = mapper.mapToReleaseResourceEvent(projectId, tenantId);
        eventProducer.sendReleaseResourceEvent(eventDto);

        return "Resource release event sent successfully.";
    }




}
