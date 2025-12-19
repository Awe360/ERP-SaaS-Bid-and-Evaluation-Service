package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.VendorIssueResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.VendorIssueResolutionStatusResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.VendorIssueResolutionStatusMapper;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.VendorIssueResolutionStatus;
import com.saas.projectDelivery.repository.VendorIssueResolutionStatusRepository;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VendorIssueResolutionStatusService {

    private final VendorIssueResolutionStatusRepository repository;
    private final VendorIssueResolutionStatusMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;

    public VendorIssueResolutionStatusResponse create(UUID tenantId, VendorIssueResolutionStatusRequest request, MultipartFile file) throws IOException {
        Document document = null;
        if (file != null && !file.isEmpty()) {
            document = documentService.upload(tenantId, file);
        }

        VendorIssueResolutionStatus entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public VendorIssueResolutionStatusResponse update(UUID tenantId, UUID id, VendorIssueResolutionStatusRequest request, MultipartFile file) throws IOException {
        VendorIssueResolutionStatus entity = validationUtil.findResolutionStatusByTenantIdAndId(tenantId, id);

        if (file != null && !file.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, file);
            entity.setResolutionDocument(newDoc);
        } else {
            entity.setResolutionDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<VendorIssueResolutionStatusResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<VendorIssueResolutionStatusResponse> getByVendorIssueId(UUID tenantId, UUID vendorKeyIssueId) {
        return repository.findByTenantIdAndVendorKeyIssueId(tenantId, vendorKeyIssueId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public VendorIssueResolutionStatusResponse getById(UUID tenantId, UUID id) {
        VendorIssueResolutionStatus entity = validationUtil.findResolutionStatusByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public UUID getResolutionDocumentId(UUID tenantId, UUID resolutionId) {
        return repository.findByTenantIdAndId(tenantId, resolutionId)
                .map(r -> r.getResolutionDocument() != null ? r.getResolutionDocument().getId() : null)
                .orElse(null);
    }
}