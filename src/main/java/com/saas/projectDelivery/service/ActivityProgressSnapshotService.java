package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.ActivityProgressSnapshotRequest;
import com.saas.projectDelivery.dto.response.ActivityProgressSnapshotResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.ActivityProgressSnapshotMapper;
import com.saas.projectDelivery.model.ActivityProgressSnapshot;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.repository.ActivityProgressSnapshotRepository;
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
public class ActivityProgressSnapshotService {

    private final ActivityProgressSnapshotRepository repository;
    private final ActivityProgressSnapshotMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;

    public ActivityProgressSnapshotResponse create(UUID tenantId, ActivityProgressSnapshotRequest request, MultipartFile file) throws IOException {
        Document document = null;
        if (file != null && !file.isEmpty()) {
            document = documentService.upload(tenantId, file);
        }

        ActivityProgressSnapshot entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ActivityProgressSnapshotResponse update(UUID tenantId, UUID id, ActivityProgressSnapshotRequest request, MultipartFile file) throws IOException {
        ActivityProgressSnapshot entity = validationUtil.findProgressByTenantIdAndId(tenantId, id);

        if (file != null && !file.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, file);
            entity.setActivityProgressDocument(newDoc);
        } else {
            entity.setActivityProgressDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<ActivityProgressSnapshotResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ActivityProgressSnapshotResponse getById(UUID tenantId, UUID id) {
        ActivityProgressSnapshot entity = validationUtil.findProgressByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        ActivityProgressSnapshot entity = validationUtil.findProgressByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }

    public UUID getActivityProgressDocumentId(UUID tenantId, UUID snapshotId) {
        return repository.findByTenantIdAndId(tenantId, snapshotId)
                .map(s -> s.getActivityProgressDocument() != null ? s.getActivityProgressDocument().getId() : null)
                .orElse(null);
    }
}