
package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.ActivityProgressSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityProgressSnapshotRepository extends JpaRepository<ActivityProgressSnapshot, UUID> {

    List<ActivityProgressSnapshot> findByTenantId(UUID tenantId);
    Optional<ActivityProgressSnapshot> findByTenantIdAndId(UUID tenantId, UUID id);

    List<ActivityProgressSnapshot> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<ActivityProgressSnapshot> findByTenantIdAndActivityId(UUID tenantId, UUID activityId);

    boolean existsByTenantIdAndId(UUID tenantId, UUID id);
}