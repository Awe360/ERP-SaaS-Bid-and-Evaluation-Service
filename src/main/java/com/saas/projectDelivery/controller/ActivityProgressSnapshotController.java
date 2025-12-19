package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.ActivityProgressSnapshotRequest;
import com.saas.projectDelivery.dto.response.ActivityProgressSnapshotResponse;
import com.saas.projectDelivery.dto.response.DocumentMetadata;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.service.ActivityProgressSnapshotService;
import com.saas.projectDelivery.service.DocumentService;
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
@RequestMapping("/api/project-delivery/activity-progress-snapshots/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "activity-progress-snapshot")
public class ActivityProgressSnapshotController {

    private final ActivityProgressSnapshotService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ActivityProgressSnapshotResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") ActivityProgressSnapshotRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addProgressSnapshotPermission(tenantId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ActivityProgressSnapshotResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProgressSnapshotsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityProgressSnapshotResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProgressSnapshotByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ActivityProgressSnapshotResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") ActivityProgressSnapshotRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateProgressSnapshotPermission(tenantId);

        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteProgressSnapshotPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadProgressAttachmentPermission(tenantId);

        UUID documentId = service.getActivityProgressDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this progress snapshot.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}