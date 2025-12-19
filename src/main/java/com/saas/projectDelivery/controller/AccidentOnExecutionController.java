package com.saas.projectDelivery.controller;

import com.saas.projectDelivery.dto.request.AccidentOnExecutionRequest;
import com.saas.projectDelivery.dto.response.AccidentOnExecutionResponse;
import com.saas.projectDelivery.dto.response.DocumentMetadata;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.service.AccidentOnExecutionService;
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
@RequestMapping("/api/project-delivery/accidents/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "accident-on-execution")
public class AccidentOnExecutionController {

    private final AccidentOnExecutionService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AccidentOnExecutionResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") AccidentOnExecutionRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addAccidentPermission(tenantId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request, file));
    }

    @GetMapping
    public ResponseEntity<List<AccidentOnExecutionResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllAccidentsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccidentOnExecutionResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getAccidentByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AccidentOnExecutionResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") AccidentOnExecutionRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateAccidentPermission(tenantId);

        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteAccidentPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadAccidentAttachmentPermission(tenantId);

        UUID documentId = service.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this accident.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}