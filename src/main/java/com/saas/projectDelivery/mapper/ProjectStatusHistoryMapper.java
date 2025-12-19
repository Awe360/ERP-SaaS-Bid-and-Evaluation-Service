package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.eventDto.ChangeProjectStatusDto;
import com.saas.projectDelivery.dto.request.ProjectStatusHistoryRequest;
import com.saas.projectDelivery.dto.response.ProjectStatusHistoryResponse;
import com.saas.projectDelivery.model.ProjectStatusHistory;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectStatusHistoryMapper {

    private final ValidationUtil validationUtil;

    public ProjectStatusHistory toEntity(UUID tenantId, ProjectStatusHistoryRequest req) {
        validationUtil.validateProjectExists(tenantId, req.getProjectId());

        ProjectStatusHistory entity = new ProjectStatusHistory();
        entity.setTenantId(tenantId);
        entity.setProjectId(req.getProjectId());
        entity.setPreviousStatus(req.getPreviousStatus());
        entity.setNewStatus(req.getNewStatus());
        entity.setComment(req.getComment());
        return entity;
    }


    public ChangeProjectStatusDto mapToChangeProjectStatusEvent(ProjectStatusHistory history) {
ChangeProjectStatusDto dto =new ChangeProjectStatusDto();
        dto.setProjectId(history.getProjectId());
        dto.setTenantId(history.getTenantId());
        dto.setPreviousStatus(history.getPreviousStatus());
        dto.setNewStatus(history.getNewStatus());
        dto.setComment(history.getComment());
        dto.setChangedBy(history.getCreatedBy());
        dto.setChangedAt(history.getCreatedAt());
        return dto;

    };
    public ProjectStatusHistoryResponse toResponse(ProjectStatusHistory entity) {
        ProjectStatusHistoryResponse res = new ProjectStatusHistoryResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setProjectId(entity.getProjectId());
        res.setPreviousStatus(entity.getPreviousStatus());
        res.setNewStatus(entity.getNewStatus());
        res.setComment(entity.getComment());

        return res;
    }
}