package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.eventDto.ChangeProjectStatusDto;
import com.saas.projectDelivery.dto.request.ProjectStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ProjectStatusHistoryResponse;
import com.saas.projectDelivery.event.ChangeProjectStatusEventProducer;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.ProjectStatusHistoryMapper;
import com.saas.projectDelivery.model.ProjectStatusHistory;
import com.saas.projectDelivery.repository.ProjectStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectStatusHistoryService {

    private final ProjectStatusHistoryRepository repository;
    private final ProjectStatusHistoryMapper mapper;
    private final ChangeProjectStatusEventProducer ChangeProjectStatusEventProducer;


    public ProjectStatusHistoryResponse create(UUID tenantId, ProjectStatusHistoryRequest request) {
        ProjectStatusHistory entity = mapper.toEntity(tenantId, request);
        ProjectStatusHistory savedEntity = repository.save(entity);
        ChangeProjectStatusDto eventDto = mapper.mapToChangeProjectStatusEvent(savedEntity);
        ChangeProjectStatusEventProducer.sendChangeProjectStatusEvent(eventDto);
        return mapper.toResponse(savedEntity);
    }

    public List<ProjectStatusHistoryResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProjectStatusHistoryResponse> getByProjectId(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjectStatusHistoryResponse getById(UUID tenantId, UUID id) {
        ProjectStatusHistory entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Status history not found"));
        return mapper.toResponse(entity);
    }
}