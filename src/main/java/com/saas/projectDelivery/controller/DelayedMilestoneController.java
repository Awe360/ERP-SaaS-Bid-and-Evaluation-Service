package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.DelayedMilestoneRequest;
import com.saas.projectDelivery.dto.response.DelayedMilestoneResponse;
import com.saas.projectDelivery.service.DelayedMilestoneService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/delayed-milestones/{tenant-id}")
@RequiredArgsConstructor
public class DelayedMilestoneController {

    private final DelayedMilestoneService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<List<DelayedMilestoneResponse>> createDelayedMilestones(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody DelayedMilestoneRequest request) {
        permissionEvaluator.addDelayedMilestonePermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createDelayedMilestones(tenantId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelayedMilestoneResponse> getDelayedMilestoneById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getDelayedMilestoneByIdPermission(tenantId);
        return ResponseEntity.ok(service.getDelayedMilestoneById(tenantId, id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DelayedMilestoneResponse>> getDelayedMilestonesByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getDelayedMilestonesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getDelayedMilestonesByProject(tenantId, projectId));
    }

    @GetMapping("/project/{projectId}/milestone/{milestoneId}")
    public ResponseEntity<List<DelayedMilestoneResponse>> getDelayedMilestonesByProjectAndMilestone(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId,
            @PathVariable UUID milestoneId) {
        permissionEvaluator.getDelayedMilestonesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getDelayedMilestonesByProjectAndMilestone(tenantId, projectId, milestoneId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DelayedMilestoneResponse> updateDelayedMilestone(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody DelayedMilestoneRequest.DelayedMilestoneItem item) {
        permissionEvaluator.updateDelayedMilestonePermission(tenantId);
        return ResponseEntity.ok(service.updateDelayedMilestone(tenantId, id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelayedMilestone(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteDelayedMilestonePermission(tenantId);
        service.deleteDelayedMilestone(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}