package com.saas.projectDelivery.controller.lookup;

import com.saas.projectDelivery.dto.request.lookup.AccidentTypeRequest;
import com.saas.projectDelivery.dto.response.lookup.AccidentTypeResponse;
import com.saas.projectDelivery.service.lookup.AccidentTypeService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/accident-types/{tenant-id}")
@RequiredArgsConstructor
public class AccidentTypeController {

    private final AccidentTypeService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<AccidentTypeResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody AccidentTypeRequest request) {
        permissionEvaluator.addAccidentTypePermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<AccidentTypeResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllAccidentTypesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccidentTypeResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getAccidentTypeByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccidentTypeResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody AccidentTypeRequest request) {
        permissionEvaluator.updateAccidentTypePermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteAccidentTypePermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.ok("Accident type deleted successfully");
    }
}