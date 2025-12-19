package com.saas.projectDelivery.controller.lookup;

import com.saas.projectDelivery.dto.request.lookup.ChangeStatusRequest;
import com.saas.projectDelivery.dto.request.lookup.CurrencyRequest;
import com.saas.projectDelivery.dto.response.lookup.CurrencyResponse;
import com.saas.projectDelivery.service.lookup.CurrencyService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/currencies/{tenant-id}")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<CurrencyResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody CurrencyRequest request) {
        permissionEvaluator.addCurrencyPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<CurrencyResponse>> getAllActive(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllActiveCurrenciesPermission(tenantId);
        return ResponseEntity.ok(service.getAllActive(tenantId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyResponse>> getAllCurrency(
            @PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllCurrenciesPermission(tenantId);
        return ResponseEntity.ok(service.getAllCurrency(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getCurrencyByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody CurrencyRequest request) {
        permissionEvaluator.updateCurrencyPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteCurrencyPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.ok("Currency deleted successfully");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CurrencyResponse> changeCurrencyStatus(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @RequestBody @Valid ChangeStatusRequest request) {
        permissionEvaluator.changeCurrencyStatusPermission(tenantId);
        return ResponseEntity.ok(service.changeStatus(tenantId, id, request.isActive()));
    }
}