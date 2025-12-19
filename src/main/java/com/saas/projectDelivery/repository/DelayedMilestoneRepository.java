package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.DelayedMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DelayedMilestoneRepository extends JpaRepository<DelayedMilestone, UUID> {
    List<DelayedMilestone> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<DelayedMilestone> findByTenantIdAndProjectIdAndMilestoneId(UUID tenantId, UUID projectId, UUID milestoneId);
    Optional<DelayedMilestone> findByIdAndTenantId(UUID id, UUID tenantId);
}