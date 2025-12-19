package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.ProjectStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ProjectStatusHistoryResponse;
import com.saas.projectDelivery.service.ProjectStatusHistoryService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/status-history/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "project-status-history")
public class ProjectStatusHistoryController {

    private final ProjectStatusHistoryService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ProjectStatusHistoryResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ProjectStatusHistoryRequest request) {
        permissionEvaluator.addProjectStatusHistoryPermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectStatusHistoryResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProjectStatusHistoriesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectStatusHistoryResponse>> getByProjectId(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getProjectStatusHistoryByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProjectId(tenantId, projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectStatusHistoryResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProjectStatusHistoryByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }
}