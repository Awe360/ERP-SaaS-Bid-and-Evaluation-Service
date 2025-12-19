package com.saas.projectDelivery.controller.lookup;

import com.saas.projectDelivery.dto.request.lookup.ResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.lookup.ResolutionStatusResponse;
import com.saas.projectDelivery.service.lookup.ResolutionStatusService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/resolution-statuses/{tenant-id}")
@RequiredArgsConstructor
public class ResolutionStatusController {

    private final ResolutionStatusService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ResolutionStatusResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ResolutionStatusRequest request) {
        permissionEvaluator.addResolutionStatusPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ResolutionStatusResponse>> getAll(
            @PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllResolutionStatusesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResolutionStatusResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getResolutionStatusByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResolutionStatusResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ResolutionStatusRequest request) {
        permissionEvaluator.updateResolutionStatusPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteResolutionStatusPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.ok("Resolution status deleted successfully");
    }
}