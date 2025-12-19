package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.DelayedMilestoneRequest;
import com.saas.projectDelivery.dto.response.DelayedMilestoneResponse;
import com.saas.projectDelivery.model.DelayedMilestone;
import com.saas.projectDelivery.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DelayedMilestoneMapper {
    private final SecurityUtil securityUtil;

    public DelayedMilestone toEntity(UUID tenantId, DelayedMilestoneRequest.DelayedMilestoneItem item) {
        DelayedMilestone entity = new DelayedMilestone();
        entity.setTenantId(tenantId);
        entity.setProjectId(item.getProjectId());
        entity.setContractorId(item.getContractorId());
        entity.setMilestoneId(item.getMilestoneId());
        entity.setDelayedPeriod(item.getDelayedPeriod());
        entity.setDelayanceReason(item.getDelayanceReason());
        entity.setScheduleEndDate(item.getScheduleEndDate());
        entity.setActionTaken(item.getActionTaken());
        entity.setDescription(item.getDescription());
        entity.setComment(item.getComment());
        return entity;
    }

    public DelayedMilestoneResponse toResponse(DelayedMilestone entity) {
        DelayedMilestoneResponse resp = new DelayedMilestoneResponse();
        resp.setId(entity.getId());
        resp.setProjectId(entity.getProjectId());
        resp.setContractorId(entity.getContractorId());
        resp.setMilestoneId(entity.getMilestoneId());
        resp.setDelayedPeriod(entity.getDelayedPeriod());
        resp.setDelayanceReason(entity.getDelayanceReason());
        resp.setScheduleEndDate(entity.getScheduleEndDate());
        resp.setActionTaken(entity.getActionTaken());
        resp.setDescription(entity.getDescription());
        resp.setComment(entity.getComment());
        resp.setTenantId(entity.getTenantId());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        resp.setCreatedBy(entity.getCreatedBy());
        resp.setUpdatedBy(entity.getUpdatedBy());
        return resp;
    }

    public void updateEntity(DelayedMilestone entity, DelayedMilestoneRequest.DelayedMilestoneItem item) {
        entity.setProjectId(item.getProjectId());
        entity.setContractorId(item.getContractorId());
        entity.setMilestoneId(item.getMilestoneId());
        entity.setDelayedPeriod(item.getDelayedPeriod());
        entity.setDelayanceReason(item.getDelayanceReason());
        entity.setScheduleEndDate(item.getScheduleEndDate());
        entity.setActionTaken(item.getActionTaken());
        entity.setDescription(item.getDescription());
        entity.setComment(item.getComment());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }
}