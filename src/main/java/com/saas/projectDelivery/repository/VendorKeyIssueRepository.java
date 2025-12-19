
package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.VendorKeyIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VendorKeyIssueRepository extends JpaRepository<VendorKeyIssue, UUID> {

    List<VendorKeyIssue> findByTenantId(UUID tenantId);
    Optional<VendorKeyIssue> findByTenantIdAndId(UUID tenantId, UUID id);

    List<VendorKeyIssue> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<VendorKeyIssue> findByTenantIdAndSupplierId(UUID tenantId, UUID supplierId);

    boolean existsByTenantIdAndId(UUID tenantId, UUID id);
}