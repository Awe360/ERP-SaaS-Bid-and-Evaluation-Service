package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.ActivityStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ActivityStatusHistoryResponse;
import com.saas.projectDelivery.service.ActivityStatusHistoryService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/activity-status-history/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "activity-status-history")
public class ActivityStatusHistoryController {

    private final ActivityStatusHistoryService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ActivityStatusHistoryResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ActivityStatusHistoryRequest request) {
        permissionEvaluator.addActivityStatusHistoryPermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityStatusHistoryResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllActivityStatusHistoriesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ActivityStatusHistoryResponse>> getByProjectId(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getActivityStatusHistoryByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProjectId(tenantId, projectId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<ActivityStatusHistoryResponse>> getByActivityId(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID activityId) {
        permissionEvaluator.getActivityStatusHistoryByActivityPermission(tenantId);
        return ResponseEntity.ok(service.getByActivityId(tenantId, activityId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityStatusHistoryResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getActivityStatusHistoryByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }
}