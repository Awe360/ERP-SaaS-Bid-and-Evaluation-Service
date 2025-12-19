package com.saas.projectDelivery.mapper;

import com.saas.projectDelivery.dto.request.ProjectActualCostRequest;
import com.saas.projectDelivery.dto.response.ProjectActualCostResponse;
import com.saas.projectDelivery.model.ProjectActualCost;
import com.saas.projectDelivery.model.lookup.Currency;
import com.saas.projectDelivery.utility.SecurityUtil;
import com.saas.projectDelivery.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectActualCostMapper {
    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ProjectActualCost toEntity(UUID tenantId, ProjectActualCostRequest request) {
        validationUtil.validateProjectExists(tenantId,request.getProjectId());
        Currency currency=validationUtil.getCurrencyById(tenantId,request.getCurrencyId());
        ProjectActualCost cost = new ProjectActualCost();
        cost.setTenantId(tenantId);
        cost.setProjectId(request.getProjectId());
        cost.setSubProjectId(request.getSubProjectId());
        cost.setProjectActivityId(request.getProjectActivityId());
        cost.setActualCost(request.getActualCost());
        cost.setCurrency(currency);
        cost.setCostDate(request.getCostDate());
        cost.setRemark(request.getRemark());
        cost.setComment(request.getComment());
        return cost;
    }

    public ProjectActualCostResponse toResponse(ProjectActualCost entity) {
        ProjectActualCostResponse response = new ProjectActualCostResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        response.setProjectId(entity.getProjectId());
        response.setSubProjectId(entity.getSubProjectId());
        response.setProjectActivityId(entity.getProjectActivityId());
        response.setActualCost(entity.getActualCost());
        response.setCurrencyId(entity.getCurrency().getId());
        response.setCostDate(entity.getCostDate());
        response.setRemark(entity.getRemark());
        response.setComment(entity.getComment());

        return response;
    }

    public void toUpdateEntity(UUID tenantId,ProjectActualCost entity, ProjectActualCostRequest request) {
        validationUtil.validateProjectExists(tenantId,request.getProjectId());
        Currency currency=validationUtil.getCurrencyById(tenantId,request.getCurrencyId());

        entity.setProjectId(request.getProjectId());
        entity.setSubProjectId(request.getSubProjectId());
        entity.setProjectActivityId(request.getProjectActivityId());
        entity.setActualCost(request.getActualCost());
        entity.setCurrency(currency);
        entity.setCostDate(request.getCostDate());
        entity.setRemark(request.getRemark());
        entity.setComment(request.getComment());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }
}