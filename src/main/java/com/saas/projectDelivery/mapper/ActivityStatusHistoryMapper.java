package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.eventDto.ChangeActivityStatusDto;
import com.saas.projectDelivery.dto.request.ActivityStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ActivityStatusHistoryResponse;
import com.saas.projectDelivery.model.ActivityStatusHistory;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActivityStatusHistoryMapper {

    private final ValidationUtil validationUtil;

    public ActivityStatusHistory toEntity(UUID tenantId, ActivityStatusHistoryRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        ActivityStatusHistory entity = new ActivityStatusHistory();
        entity.setTenantId(tenantId);
        entity.setProjectId(req.getProjectId());
        entity.setSubProjectId(req.getSubProjectId());
        entity.setActivityId(req.getActivityId());
        entity.setOldStatus(req.getOldStatus());
        entity.setNewStatus(req.getNewStatus());
        entity.setComment(req.getComment());
        return entity;
    }


    public ChangeActivityStatusDto mapToChangeActivityStatusEvent(ActivityStatusHistory history) {
        ChangeActivityStatusDto dto = new ChangeActivityStatusDto();
        dto.setProjectId(history.getProjectId());
        dto.setSubProjectId(history.getSubProjectId());
        dto.setActivityId(history.getActivityId());
        dto.setTenantId(history.getTenantId());
        dto.setOldStatus(history.getOldStatus());
        dto.setNewStatus(history.getNewStatus());
        dto.setComment(history.getComment());
        dto.setChangedBy(history.getCreatedBy());
        dto.setChangedAt(history.getCreatedAt());
        return dto;

    };

    public ActivityStatusHistoryResponse toResponse(ActivityStatusHistory entity) {
        ActivityStatusHistoryResponse res = new ActivityStatusHistoryResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProjectId(entity.getProjectId());
        res.setSubProjectId(entity.getSubProjectId());
        res.setActivityId(entity.getActivityId());
        res.setOldStatus(entity.getOldStatus());
        res.setNewStatus(entity.getNewStatus());
        res.setComment(entity.getComment());
        return res;
    }
}