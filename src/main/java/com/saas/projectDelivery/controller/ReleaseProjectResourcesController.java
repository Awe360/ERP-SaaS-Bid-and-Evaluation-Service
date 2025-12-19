package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.service.ReleaseResourceService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/release-resource/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "release-project-resources")
public class ReleaseProjectResourcesController {

    private final ReleaseResourceService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/release/{project-id}")
    public ResponseEntity<?> releaseProjectResources(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable("project-id") UUID projectId) {
        permissionEvaluator.releaseProjectResourcesPermission(tenantId);
        service.releaseResources(tenantId, projectId);
        return ResponseEntity.ok("Resources released successfully");
    }
}