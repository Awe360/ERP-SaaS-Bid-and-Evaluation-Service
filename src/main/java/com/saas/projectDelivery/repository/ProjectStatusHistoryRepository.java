package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.ProjectStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectStatusHistoryRepository extends JpaRepository<ProjectStatusHistory, UUID> {
    List<ProjectStatusHistory> findByTenantId(UUID tenantId);
    List<ProjectStatusHistory> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    Optional<ProjectStatusHistory> findByTenantIdAndId(UUID tenantId, UUID id);
}