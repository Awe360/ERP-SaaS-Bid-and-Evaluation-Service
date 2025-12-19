package com.saas.projectDelivery.repository.lookup;


import com.saas.projectDelivery.model.lookup.AccidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccidentTypeRepository extends JpaRepository<AccidentType, UUID> {

    List<AccidentType> findAllByTenantId(UUID tenantId);

    Optional<AccidentType> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndTypeNameIgnoreCase(UUID tenantId, String typeName);
}
