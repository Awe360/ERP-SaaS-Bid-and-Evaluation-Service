package com.saas.projectDelivery.repository.lookup;


import com.saas.projectDelivery.model.lookup.ResolutionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResolutionStatusRepository extends JpaRepository<ResolutionStatus, UUID> {

    List<ResolutionStatus> findAllByTenantId(UUID tenantId);

    Optional<ResolutionStatus> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndStatusNameIgnoreCase(UUID tenantId, String statusName);
}