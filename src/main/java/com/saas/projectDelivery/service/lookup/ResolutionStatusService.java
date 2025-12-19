package com.saas.projectDelivery.service.lookup;


import com.saas.projectDelivery.dto.request.lookup.ResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.lookup.ResolutionStatusResponse;
import com.saas.projectDelivery.exception.ResourceExistsException;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.lookup.ResolutionStatusMapper;
import com.saas.projectDelivery.model.lookup.ResolutionStatus;
import com.saas.projectDelivery.repository.lookup.ResolutionStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResolutionStatusService {

    private final ResolutionStatusRepository repository;
    private final ResolutionStatusMapper mapper;

    @Transactional
    public ResolutionStatusResponse create(UUID tenantId, ResolutionStatusRequest request) {
        String name = request.getStatusName().trim();
        if (repository.existsByTenantIdAndStatusNameIgnoreCase(tenantId, name)) {
            throw new IllegalArgumentException("Resolution status already exists: " + name);
        }

        ResolutionStatus entity = mapper.toEntity(tenantId, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public List<ResolutionStatusResponse> getAll(UUID tenantId) {
        return repository.findAllByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ResolutionStatusResponse getById(UUID tenantId, UUID id) {
        ResolutionStatus entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution status not found"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public ResolutionStatusResponse update(UUID tenantId, UUID id, ResolutionStatusRequest request) {
        ResolutionStatus entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution status not found"));

        String newName = request.getStatusName().trim();
        if (!entity.getStatusName().equalsIgnoreCase(newName) &&
                repository.existsByTenantIdAndStatusNameIgnoreCase(tenantId, newName)) {
            throw new ResourceExistsException("Resolution status already exists: " + newName);
        }

        mapper.updateEntity(entity, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID tenantId, UUID id) {
        ResolutionStatus entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution status not found"));
        repository.delete(entity);
    }
}