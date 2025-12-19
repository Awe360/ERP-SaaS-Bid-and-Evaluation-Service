package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.DelayedActivityRequest;
import com.saas.projectDelivery.dto.response.DelayedActivityResponse;
import com.saas.projectDelivery.service.DelayedActivityService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/delayed-activities/{tenant-id}")
@RequiredArgsConstructor
public class DelayedActivityController {

    private final DelayedActivityService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<List<DelayedActivityResponse>> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody DelayedActivityRequest request) {
        permissionEvaluator.addDelayedActivityPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelayedActivityResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getDelayedActivityByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DelayedActivityResponse>> getByProjectId(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getDelayedActivitiesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProjectId(tenantId, projectId));
    }

    @GetMapping("/project/{projectId}/milestone/{milestoneId}")
    public ResponseEntity<List<DelayedActivityResponse>> getByProjectAndMilestone(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId,
            @PathVariable UUID milestoneId) {
        permissionEvaluator.getDelayedActivitiesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProjectAndMilestone(tenantId, projectId, milestoneId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DelayedActivityResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody DelayedActivityRequest.DelayedActivityItem item) {
        permissionEvaluator.updateDelayedActivityPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteDelayedActivityPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}