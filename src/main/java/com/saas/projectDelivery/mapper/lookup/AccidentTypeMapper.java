package com.saas.projectDelivery.mapper.lookup;

import com.saas.projectDelivery.dto.request.lookup.AccidentTypeRequest;
import com.saas.projectDelivery.dto.response.lookup.AccidentTypeResponse;
import com.saas.projectDelivery.model.lookup.AccidentType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccidentTypeMapper {

    public AccidentType toEntity(UUID tenantId, AccidentTypeRequest request) {
        AccidentType type = new AccidentType();
        type.setTenantId(tenantId);
        type.setTypeName(request.getTypeName().trim());
        return type;
    }

    public void updateEntity(AccidentType entity, AccidentTypeRequest request) {
        entity.setTypeName(request.getTypeName().trim());
    }

    public AccidentTypeResponse toResponse(AccidentType entity) {
        AccidentTypeResponse response = new AccidentTypeResponse();

        response.setId(entity.getId());
        response.setTypeName(entity.getTypeName());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        return response;

    }
}
