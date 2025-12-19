package com.saas.projectDelivery.client;


import com.saas.projectDelivery.dto.clientDto.ActivityDto;
import com.saas.projectDelivery.dto.clientDto.ProjectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "project-initiation-service", path = "/api/project-initiation")

public interface ProjectInitiationServiceClient {


    @GetMapping("/project-initiations/{tenant-id}/projects/{project-id}")
    ProjectDto getProjectById(@PathVariable("tenant-id") UUID tenantId,
                              @PathVariable("project-id") UUID projectId);

    @GetMapping("/project-initiations/{tenant-id}/activities/{activity-id}")
    ActivityDto getActivityById(@PathVariable("tenant-id") UUID tenantId,
                                @PathVariable("activity-id") UUID activityId);

}
