package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.VendorIssueResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.VendorIssueResolutionStatusResponse;
import com.saas.projectDelivery.dto.response.DocumentMetadata;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.service.DocumentService;
import com.saas.projectDelivery.service.VendorIssueResolutionStatusService;
import com.saas.projectDelivery.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-delivery/vendor-issue-resolutions/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "vendor-issue-resolution-status")
public class VendorIssueResolutionStatusController {

    private final VendorIssueResolutionStatusService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VendorIssueResolutionStatusResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") VendorIssueResolutionStatusRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addVendorResolutionPermission(tenantId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request, file));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VendorIssueResolutionStatusResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") VendorIssueResolutionStatusRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateVendorResolutionPermission(tenantId);

        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @GetMapping
    public ResponseEntity<List<VendorIssueResolutionStatusResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllVendorResolutionsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/issue/{vendorKeyIssueId}")
    public ResponseEntity<List<VendorIssueResolutionStatusResponse>> getByIssueId(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID vendorKeyIssueId) {
        permissionEvaluator.getVendorResolutionsByIssuePermission(tenantId);
        return ResponseEntity.ok(service.getByVendorIssueId(tenantId, vendorKeyIssueId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorIssueResolutionStatusResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getVendorResolutionByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadVendorResolutionAttachmentPermission(tenantId);

        UUID documentId = service.getResolutionDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No file attached to this resolution status update.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}