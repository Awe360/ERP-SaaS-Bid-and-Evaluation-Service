package com.saas.projectDelivery.service;

import com.saas.projectDelivery.dto.request.DelayedMilestoneRequest;
import com.saas.projectDelivery.dto.response.DelayedMilestoneResponse;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.DelayedMilestoneMapper;
import com.saas.projectDelivery.model.DelayedMilestone;
import com.saas.projectDelivery.repository.DelayedMilestoneRepository;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DelayedMilestoneService {

    private final DelayedMilestoneRepository repository;
    private final DelayedMilestoneMapper mapper;
    private final ValidationUtil validationUtil;

    public List<DelayedMilestoneResponse> createDelayedMilestones(UUID tenantId, DelayedMilestoneRequest request) {
        List<DelayedMilestone> entities = request.getDelayedMilestones().stream()
                .map(item -> {
                    validationUtil.validateProjectExists(tenantId, item.getProjectId());
                    return mapper.toEntity(tenantId, item);
                })
                .toList();

        List<DelayedMilestone> saved = repository.saveAll(entities);
        return saved.stream().map(mapper::toResponse).toList();
    }

    public DelayedMilestoneResponse getDelayedMilestoneById(UUID tenantId, UUID id) {
        DelayedMilestone entity = validationUtil.findDelayedMilestoneByTenantIdAndId(tenantId, id);
        return mapper.toResponse(entity);
    }

    public List<DelayedMilestoneResponse> getDelayedMilestonesByProject(UUID tenantId, UUID projectId) {
        validationUtil.validateProjectExists(tenantId, projectId);
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<DelayedMilestoneResponse> getDelayedMilestonesByProjectAndMilestone(UUID tenantId, UUID projectId, UUID milestoneId) {
        validationUtil.validateProjectExists(tenantId, projectId);
        return repository.findByTenantIdAndProjectIdAndMilestoneId(tenantId, projectId, milestoneId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public DelayedMilestoneResponse updateDelayedMilestone(UUID tenantId, UUID id, DelayedMilestoneRequest.DelayedMilestoneItem item) {
        DelayedMilestone entity = validationUtil.findDelayedMilestoneByTenantIdAndId(tenantId, id);

        validationUtil.validateProjectExists(tenantId, item.getProjectId());
        mapper.updateEntity(entity, item);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void deleteDelayedMilestone(UUID tenantId, UUID id) {
        DelayedMilestone entity = validationUtil.findDelayedMilestoneByTenantIdAndId(tenantId, id);
        repository.delete(entity);
    }
}