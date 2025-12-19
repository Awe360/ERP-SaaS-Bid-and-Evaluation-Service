
package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.ProjectClosing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectClosingRepository extends JpaRepository<ProjectClosing, UUID> {

    List<ProjectClosing> findByTenantId(UUID tenantId);

    Optional<ProjectClosing> findByTenantIdAndId(UUID tenantId, UUID id);

    Optional<ProjectClosing> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    boolean existsByTenantIdAndProjectId(UUID tenantId, UUID projectId);
}