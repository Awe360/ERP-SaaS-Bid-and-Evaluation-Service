package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.VendorKeyIssueRequest;
import com.saas.projectDelivery.dto.response.VendorKeyIssueResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.VendorKeyIssueMapper;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.VendorKeyIssue;
import com.saas.projectDelivery.repository.VendorKeyIssueRepository;
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
public class VendorKeyIssueService {

    private final VendorKeyIssueRepository repository;
    private final VendorKeyIssueMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;

    public VendorKeyIssueResponse create(UUID tenantId, VendorKeyIssueRequest request, MultipartFile file) throws IOException {
        Document document = null;
        if (file != null && !file.isEmpty()) {
            document = documentService.upload(tenantId, file);
        }

        VendorKeyIssue entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public VendorKeyIssueResponse update(UUID tenantId, UUID id, VendorKeyIssueRequest request, MultipartFile file) throws IOException {
        VendorKeyIssue entity = validationUtil.findVendorKeyIssueByTenantIdAndId(tenantId, id);

        if (file != null && !file.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, file);
            entity.setVendorIssueDocument(newDoc);
        } else {
            entity.setVendorIssueDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        VendorKeyIssue  savedEntity = repository.save(entity);

        return mapper.toResponse(savedEntity);
    }

    public List<VendorKeyIssueResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public VendorKeyIssueResponse getById(UUID tenantId, UUID id) {
        VendorKeyIssue entity = validationUtil.findVendorKeyIssueByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        VendorKeyIssue entity = validationUtil.findVendorKeyIssueByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }

    public UUID getVendorIssueDocumentId(UUID tenantId, UUID issueId) {
        return repository.findByTenantIdAndId(tenantId, issueId)
                .map(issue -> issue.getVendorIssueDocument() != null ? issue.getVendorIssueDocument().getId() : null)
                .orElse(null);
    }
}