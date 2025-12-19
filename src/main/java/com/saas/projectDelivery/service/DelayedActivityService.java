package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.DelayedActivityRequest;
import com.saas.projectDelivery.dto.response.DelayedActivityResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.DelayedActivityMapper;
import com.saas.projectDelivery.model.DelayedActivity;
import com.saas.projectDelivery.repository.DelayedActivityRepository;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DelayedActivityService {

    private final DelayedActivityRepository repository;
    private final DelayedActivityMapper mapper;
    private final ValidationUtil validationUtil;

    public List<DelayedActivityResponse> create(UUID tenantId, DelayedActivityRequest request) {
        List<DelayedActivity> entities = request.getDelayedActivities().stream()
                .map(item -> {
                    validationUtil.validateProjectExists(tenantId, item.getProjectId());
                    return mapper.toEntity(tenantId, item);
                })
                .toList();

        List<DelayedActivity> saved = repository.saveAll(entities);
        return saved.stream().map(mapper::toResponse).toList();
    }

    public DelayedActivityResponse getById(UUID tenantId, UUID id) {
        DelayedActivity entity = validationUtil.findDelayedActivityByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public List<DelayedActivityResponse> getByProjectId(UUID tenantId, UUID projectId) {
        validationUtil.validateProjectExists(tenantId, projectId);
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<DelayedActivityResponse> getByProjectAndMilestone(UUID tenantId, UUID projectId, UUID milestoneId) {
        validationUtil.validateProjectExists(tenantId, projectId);
        return repository.findByTenantIdAndProjectIdAndDelayedActivityId(tenantId, projectId, milestoneId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public DelayedActivityResponse update(UUID tenantId, UUID id, DelayedActivityRequest.DelayedActivityItem item) {
        DelayedActivity entity = validationUtil.findDelayedActivityByTenantIdAndId(tenantId, id);

        validationUtil.validateProjectExists(tenantId, item.getProjectId());
        mapper.updateEntity(entity, item);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        DelayedActivity entity = validationUtil.findDelayedActivityByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }
}