package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.eventDto.ReleaseResourceDto;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ReleaseResourceMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ReleaseResourceDto mapToReleaseResourceEvent(UUID projectId, UUID tenantId) {
        validationUtil.validateProjectExists(tenantId, projectId);

        ReleaseResourceDto eventDto = new ReleaseResourceDto();
        eventDto.setTenantId(tenantId);
        eventDto.setProjectId(projectId);
        eventDto.setReleasedBy(securityUtil.getName());
        eventDto.setReleasedAt(LocalDateTime.now());
        eventDto.setMessage("Resources released for project " + projectId);
        return eventDto;
    }
}
