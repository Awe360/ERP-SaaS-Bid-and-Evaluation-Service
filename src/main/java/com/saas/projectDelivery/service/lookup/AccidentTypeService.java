package com.saas.projectDelivery.service.lookup;


import com.saas.projectDelivery.dto.request.lookup.AccidentTypeRequest;
import com.saas.projectDelivery.dto.response.lookup.AccidentTypeResponse;
import com.saas.projectDelivery.exception.ResourceExistsException;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.lookup.AccidentTypeMapper;
import com.saas.projectDelivery.model.lookup.AccidentType;
import com.saas.projectDelivery.repository.lookup.AccidentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeRepository repository;
    private final AccidentTypeMapper mapper;

    @Transactional
    public AccidentTypeResponse create(UUID tenantId, AccidentTypeRequest request) {
        if (repository.existsByTenantIdAndTypeNameIgnoreCase(tenantId, request.getTypeName())) {
            throw new IllegalArgumentException("Accident type already exists: " + request.getTypeName());
        }

        AccidentType entity = mapper.toEntity(tenantId, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public List<AccidentTypeResponse> getAll(UUID tenantId) {
        return repository.findAllByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public AccidentTypeResponse getById(UUID tenantId, UUID id) {
        AccidentType entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Accident type not found"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public AccidentTypeResponse update(UUID tenantId, UUID id, AccidentTypeRequest request) {
        AccidentType entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Accident type not found"));

        if (!entity.getTypeName().equalsIgnoreCase(request.getTypeName()) &&
                repository.existsByTenantIdAndTypeNameIgnoreCase(tenantId, request.getTypeName())) {
            throw new ResourceExistsException("Accident type already exists: " + request.getTypeName());
        }

        mapper.updateEntity(entity, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID tenantId, UUID id) {
        AccidentType entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Accident type not found"));
        repository.delete(entity);
    }
}