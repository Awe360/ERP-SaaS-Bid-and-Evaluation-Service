package com.saas.projectDelivery.mapper.lookup;

import com.saas.projectDelivery.dto.request.lookup.ResolutionStatusRequest;
import com.saas.projectDelivery.dto.response.lookup.ResolutionStatusResponse;
import com.saas.projectDelivery.model.lookup.ResolutionStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionStatusMapper {

    public ResolutionStatus toEntity(UUID tenantId, ResolutionStatusRequest request) {
        ResolutionStatus status = new ResolutionStatus();
        status.setTenantId(tenantId);
        status.setStatusName(request.getStatusName().trim());
        return status;
    }

    public void updateEntity(ResolutionStatus entity, ResolutionStatusRequest request) {
        entity.setStatusName(request.getStatusName().trim());
    }

    public ResolutionStatusResponse toResponse(ResolutionStatus entity) {

      ResolutionStatusResponse response = new ResolutionStatusResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setStatusName(entity.getStatusName());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
