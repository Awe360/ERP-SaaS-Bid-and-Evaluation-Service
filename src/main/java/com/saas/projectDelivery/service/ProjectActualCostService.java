package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.ProjectActualCostRequest;
import com.saas.projectDelivery.dto.response.ProjectActualCostResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.ProjectActualCostMapper;
import com.saas.projectDelivery.model.ProjectActualCost;
import com.saas.projectDelivery.repository.ProjectActualCostRepository;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectActualCostService {

    private final ProjectActualCostRepository repository;
    private final ProjectActualCostMapper mapper;
    private final ValidationUtil validationUtil;

    @Transactional
    public ProjectActualCostResponse registerActualCost(UUID tenantId, ProjectActualCostRequest request) {
        validationUtil.validateProjectExists(tenantId, request.getProjectId());
        validationUtil.validateProjectActivityExists(tenantId, request.getProjectActivityId());
        ProjectActualCost entity = mapper.toEntity(tenantId, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public List<ProjectActualCostResponse> getAllByProject(UUID tenantId, UUID projectId) {
        validationUtil.validateProjectExists(tenantId, projectId);
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProjectActualCostResponse> getByActivity(UUID tenantId, UUID activityId) {
        validationUtil.validateProjectActivityExists(tenantId, activityId);
        return repository.findByTenantIdAndProjectActivityId(tenantId, activityId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ProjectActualCostResponse> search(UUID tenantId,
                                                  UUID projectId,
                                                  UUID activityId,
                                                  LocalDate startDate,
                                                  LocalDate endDate) {
        return repository.searchActualCosts(tenantId, projectId, activityId, startDate, endDate)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjectActualCostResponse getById(UUID tenantId, UUID id) {
        ProjectActualCost entity = repository.findById(id)
                .filter(e -> e.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Actual cost not found with ID: " + id));
        return mapper.toResponse(entity);
    }

    @Transactional
    public ProjectActualCostResponse update(UUID tenantId, UUID id, ProjectActualCostRequest request) {
        ProjectActualCost entity = repository.findById(id)
                .filter(e -> e.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Actual cost not found"));

        mapper.toUpdateEntity(tenantId,entity, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID tenantId, UUID id) {
        ProjectActualCost entity = repository.findById(id)
                .filter(e -> e.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Actual cost not found"));
        repository.delete(entity);
    }
}