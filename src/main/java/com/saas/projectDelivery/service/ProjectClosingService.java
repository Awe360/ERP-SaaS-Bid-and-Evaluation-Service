package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.ProjectClosingRequest;
import com.saas.projectDelivery.dto.response.ProjectClosingResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.ProjectClosingMapper;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.model.ProjectClosing;
import com.saas.projectDelivery.repository.ProjectClosingRepository;
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
public class ProjectClosingService {

    private final ProjectClosingRepository repository;
    private final ProjectClosingMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;

    public ProjectClosingResponse create(UUID tenantId, ProjectClosingRequest request, MultipartFile file) throws IOException {
        if (repository.existsByTenantIdAndProjectId(tenantId, request.getProjectId())) {
            throw new IllegalStateException("Project has already been closed");
        }

        Document document = null;
        if (file != null && !file.isEmpty()) {
            document = documentService.upload(tenantId, file);
        }

        ProjectClosing entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ProjectClosingResponse update(UUID tenantId, UUID id, ProjectClosingRequest request, MultipartFile file) throws IOException {
        ProjectClosing entity = validationUtil.findProjectClosingByTenantIdAndId(tenantId, id);

        if (file != null && !file.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, file);
            entity.setClosingDocument(newDoc);
        } else {
            entity.setClosingDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<ProjectClosingResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjectClosingResponse getById(UUID tenantId, UUID id) {
        ProjectClosing entity = validationUtil.findProjectClosingByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public ProjectClosingResponse getByProjectId(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .map(mapper::toResponse)
                .orElse(null);
    }

    public void delete(UUID tenantId, UUID id) {
        ProjectClosing entity = validationUtil.findProjectClosingByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }

    public UUID getClosingDocumentId(UUID tenantId, UUID closingId) {
        return repository.findByTenantIdAndId(tenantId, closingId)
                .map(pc -> pc.getClosingDocument() != null ? pc.getClosingDocument().getId() : null)
                .orElse(null);
    }
}