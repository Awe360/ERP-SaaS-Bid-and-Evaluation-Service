package com.saas.projectDelivery.mapper.lookup;

import com.saas.projectDelivery.dto.request.lookup.CurrencyRequest;
import com.saas.projectDelivery.dto.response.lookup.CurrencyResponse;
import com.saas.projectDelivery.model.lookup.Currency;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrencyMapper {

    public Currency toEntity(UUID tenantId, CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setTenantId(tenantId);
        currency.setCurrencyCode(request.getCurrencyCode().toUpperCase());
        currency.setCurrencyName(request.getCurrencyName());
        currency.setSymbol(request.getSymbol());
        currency.setActive(request.isActive());
        return currency;
    }

    public void toUpdateEntity(Currency entity, CurrencyRequest request) {
        entity.setCurrencyCode(request.getCurrencyCode().toUpperCase());
        entity.setCurrencyName(request.getCurrencyName());
        entity.setSymbol(request.getSymbol());
        entity.setActive(request.isActive());
    }

    public CurrencyResponse toResponse(Currency entity) {
       CurrencyResponse response = new CurrencyResponse();
       response.setId(entity.getId());
       response.setTenantId(entity.getTenantId());
       response.setCreatedAt(entity.getCreatedAt());
       response.setUpdatedAt(entity.getUpdatedAt());
       response.setCreatedBy(entity.getCreatedBy());
       response.setUpdatedBy(entity.getUpdatedBy());
       response.setCurrencyCode(entity.getCurrencyCode());
       response.setCurrencyName(entity.getCurrencyName());
       response.setSymbol(entity.getSymbol());
       response.setActive(entity.isActive());
       return response;
    }
}