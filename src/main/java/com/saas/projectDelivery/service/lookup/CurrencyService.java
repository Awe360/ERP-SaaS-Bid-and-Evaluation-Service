package com.saas.projectDelivery.service.lookup;


import com.saas.projectDelivery.dto.request.lookup.CurrencyRequest;
import com.saas.projectDelivery.dto.response.lookup.CurrencyResponse;
import com.saas.projectDelivery.exception.ResourceExistsException;
import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.mapper.lookup.CurrencyMapper;
import com.saas.projectDelivery.model.lookup.Currency;
import com.saas.projectDelivery.repository.lookup.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;
    private final CurrencyMapper mapper;

    @Transactional
    public CurrencyResponse create(UUID tenantId, CurrencyRequest request) {
        if (repository.existsByTenantIdAndCurrencyCodeIgnoreCase(tenantId, request.getCurrencyCode())) {
            throw new IllegalArgumentException("Currency code already exists: " + request.getCurrencyCode());
        }
        Currency entity = mapper.toEntity(tenantId, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public List<CurrencyResponse> getAllActive(UUID tenantId) {
        return repository.findByTenantIdAndActiveTrue(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public List<CurrencyResponse> getAllCurrency(UUID tenantId) {
        return repository.findAllByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public CurrencyResponse getById(UUID tenantId, UUID id) {
        Currency entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with ID: " + id));
        return mapper.toResponse(entity);
    }

    @Transactional
    public CurrencyResponse update(UUID tenantId, UUID id, CurrencyRequest request) {
        Currency entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));


        repository.findByTenantIdAndCurrencyCodeIgnoreCase(tenantId, request.getCurrencyCode())
                .ifPresent(c -> {
                    if (!c.getId().equals(id)) {
                        throw new ResourceExistsException("Currency code already exists: " + request.getCurrencyCode());
                    }
                });

        mapper.toUpdateEntity(entity, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID tenantId, UUID id) {
        Currency entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));
        repository.delete(entity);
    }

    @Transactional
    public CurrencyResponse changeStatus(UUID tenantId, UUID id, boolean active) {
        Currency entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with ID: " + id));

        entity.setActive(active);
        return mapper.toResponse(repository.save(entity));
    }
}