package com.saas.projectDelivery.repository;


import com.saas.projectDelivery.model.AccidentOnExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccidentOnExecutionRepository extends JpaRepository<AccidentOnExecution, UUID> {

    Optional<AccidentOnExecution> findByTenantIdAndId(UUID tenantId, UUID id);

    List<AccidentOnExecution> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    List<AccidentOnExecution> findByTenantIdAndSubProjectId(UUID tenantId, UUID subProjectId);

    List<AccidentOnExecution> findByTenantId(UUID tenantId);

    @Query("SELECT COUNT(a) FROM AccidentOnExecution a WHERE a.tenantId = :tenantId AND a.projectId = :projectId")
    Long countByTenantIdAndProjectId(@Param("tenantId") UUID tenantId, @Param("projectId") UUID projectId);

    boolean existsByTenantIdAndId(UUID tenantId, UUID id);
}