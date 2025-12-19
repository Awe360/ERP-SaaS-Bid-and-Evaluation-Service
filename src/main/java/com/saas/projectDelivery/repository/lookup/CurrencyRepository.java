package com.saas.projectDelivery.repository.lookup;

import com.saas.projectDelivery.model.lookup.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    List<Currency> findByTenantIdAndActiveTrue(UUID tenantId);
    Optional<Currency> findByTenantIdAndIdAndActiveTrue(UUID tenantId, UUID id);


    Optional<Currency> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndCurrencyCodeIgnoreCase(UUID tenantId, String currencyCode);

    Optional<Currency> findByTenantIdAndCurrencyCodeIgnoreCase(UUID tenantId, String code);

    List<Currency> findAllByTenantId(UUID tenantId);
}