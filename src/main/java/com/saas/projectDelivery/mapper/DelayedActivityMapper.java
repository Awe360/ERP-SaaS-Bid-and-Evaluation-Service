package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.DelayedActivityRequest;
import com.saas.projectDelivery.dto.response.DelayedActivityResponse;
import com.saas.projectDelivery.model.DelayedActivity;
import com.saas.projectDelivery.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DelayedActivityMapper {
    private final SecurityUtil securityUtil;

    public DelayedActivity toEntity(UUID tenantId, DelayedActivityRequest.DelayedActivityItem item) {
        DelayedActivity entity = new DelayedActivity();
        entity.setTenantId(tenantId);
        entity.setProjectId(item.getProjectId());
        entity.setContractorId(item.getContractorId());
        entity.setDelayedActivityId(item.getDelayedActivityId());
        entity.setDelayedPeriod(item.getDelayedPeriod());
        entity.setDelayanceReason(item.getDelayanceReason());
        entity.setScheduleEndDate(item.getScheduleEndDate());
        entity.setActionTaken(item.getActionTaken());
        entity.setDescription(item.getDescription());
        entity.setComment(item.getComment());
        return entity;
    }

    public DelayedActivityResponse toResponse(DelayedActivity entity) {
        DelayedActivityResponse resp = new DelayedActivityResponse();
        resp.setId(entity.getId());
        resp.setTenantId(entity.getTenantId());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());
        resp.setCreatedBy(entity.getCreatedBy());
        resp.setUpdatedBy(entity.getUpdatedBy());
        resp.setProjectId(entity.getProjectId());
        resp.setContractorId(entity.getContractorId());
        resp.setDelayedActivityId(entity.getDelayedActivityId());
        resp.setDelayedPeriod(entity.getDelayedPeriod());
        resp.setDelayanceReason(entity.getDelayanceReason());
        resp.setScheduleEndDate(entity.getScheduleEndDate());
        resp.setActionTaken(entity.getActionTaken());
        resp.setDescription(entity.getDescription());
        resp.setComment(entity.getComment());
        return resp;
    }

    public void updateEntity(DelayedActivity entity, DelayedActivityRequest.DelayedActivityItem item) {
        entity.setProjectId(item.getProjectId());
        entity.setContractorId(item.getContractorId());
        entity.setDelayedActivityId(item.getDelayedActivityId());
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