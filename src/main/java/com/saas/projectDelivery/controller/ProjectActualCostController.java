package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.ProjectActualCostRequest;
import com.saas.projectDelivery.dto.response.ProjectActualCostResponse;
import com.saas.projectDelivery.service.ProjectActualCostService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/actual-costs/{tenant-id}")
@RequiredArgsConstructor
public class ProjectActualCostController {

    private final ProjectActualCostService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ProjectActualCostResponse> register(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ProjectActualCostRequest request) {
        permissionEvaluator.addActualCostPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerActualCost(tenantId, request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectActualCostResponse>> getByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getActualCostsByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getAllByProject(tenantId, projectId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<ProjectActualCostResponse>> getByActivity(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID activityId) {
        permissionEvaluator.getActualCostsByActivityPermission(tenantId);
        return ResponseEntity.ok(service.getByActivity(tenantId, activityId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectActualCostResponse>> search(
            @PathVariable("tenant-id") UUID tenantId,
            @RequestParam(required = false) UUID projectId,
            @RequestParam(required = false) UUID activityId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        permissionEvaluator.searchActualCostsPermission(tenantId);
        return ResponseEntity.ok(service.search(tenantId, projectId, activityId, startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectActualCostResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getActualCostByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectActualCostResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ProjectActualCostRequest request) {
        permissionEvaluator.updateActualCostPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteActualCostPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.ok("Actual cost deleted successfully");
    }
}