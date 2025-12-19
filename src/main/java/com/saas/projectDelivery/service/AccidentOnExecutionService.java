package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.AccidentOnExecutionRequest;
import com.saas.projectDelivery.dto.response.AccidentOnExecutionResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.AccidentOnExecutionMapper;
import com.saas.projectDelivery.model.AccidentOnExecution;
import com.saas.projectDelivery.model.Document;
import com.saas.projectDelivery.repository.AccidentOnExecutionRepository;
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
public class AccidentOnExecutionService {

    private final AccidentOnExecutionRepository repository;
    private final AccidentOnExecutionMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;


    public AccidentOnExecutionResponse create(UUID tenantId, AccidentOnExecutionRequest request, MultipartFile attachment) throws IOException {
        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        AccidentOnExecution entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public AccidentOnExecutionResponse update(UUID tenantId, UUID id, AccidentOnExecutionRequest request, MultipartFile attachment) throws IOException {
        AccidentOnExecution entity = validationUtil.findAccidentByTenantIdAndId(tenantId, id);

        if (attachment != null && !attachment.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, attachment);
            entity.setAttachmentDocument(newDoc);
        } else {
            entity.setAttachmentDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<AccidentOnExecutionResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public AccidentOnExecutionResponse getById(UUID tenantId, UUID id) {
        AccidentOnExecution entity = validationUtil.findAccidentByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        AccidentOnExecution entity = validationUtil.findAccidentByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID accidentId) {
        return repository.findByTenantIdAndId(tenantId, accidentId)
                .map(a -> a.getAttachmentDocument() != null ? a.getAttachmentDocument().getId() : null)
                .orElse(null);
    }
}