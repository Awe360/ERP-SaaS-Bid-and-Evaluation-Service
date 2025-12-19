package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.ActivityStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityStatusHistoryRepository extends JpaRepository<ActivityStatusHistory, UUID> {

    List<ActivityStatusHistory> findByTenantId(UUID tenantId);

    List<ActivityStatusHistory> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    List<ActivityStatusHistory> findByTenantIdAndActivityIdOrderByCreatedAtDesc(UUID tenantId, UUID activityId);

    Optional<ActivityStatusHistory> findByTenantIdAndId(UUID tenantId, UUID id);
}