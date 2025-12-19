package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.DelayedActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DelayedActivityRepository extends JpaRepository<DelayedActivity, UUID> {
    List<DelayedActivity> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<DelayedActivity> findByTenantIdAndProjectIdAndDelayedActivityId(UUID tenantId, UUID projectId, UUID delayedActivityId);
    Optional<DelayedActivity> findByIdAndTenantId(UUID id, UUID tenantId);
}