package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.eventDto.ChangeActivityStatusDto;
import com.saas.projectDelivery.dto.request.ActivityStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ActivityStatusHistoryResponse;
import com.saas.projectDelivery.event.ChangeActivityStatusEventProducer;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.ActivityStatusHistoryMapper;
import com.saas.projectDelivery.model.ActivityStatusHistory;
import com.saas.projectDelivery.repository.ActivityStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityStatusHistoryService {

    private final ActivityStatusHistoryRepository repository;
    private final ActivityStatusHistoryMapper mapper;
    private final ChangeActivityStatusEventProducer changeActivityStatusEventProducer;

    public ActivityStatusHistoryResponse create(UUID tenantId, ActivityStatusHistoryRequest request) {
        ActivityStatusHistory entity = mapper.toEntity(tenantId, request);
        ActivityStatusHistory savedEntity = repository.save(entity);
        ChangeActivityStatusDto eventDto = mapper.mapToChangeActivityStatusEvent(savedEntity);
        changeActivityStatusEventProducer.sendChangeActivityStatusEvent(eventDto);
        return mapper.toResponse(savedEntity);
    }

    public List<ActivityStatusHistoryResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ActivityStatusHistoryResponse> getByProjectId(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ActivityStatusHistoryResponse> getByActivityId(UUID tenantId, UUID activityId) {
        return repository.findByTenantIdAndActivityIdOrderByCreatedAtDesc(tenantId, activityId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ActivityStatusHistoryResponse getById(UUID tenantId, UUID id) {
        ActivityStatusHistory entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity status history not found"));
        return mapper.toResponse(entity);
    }
}