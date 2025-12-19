package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.VendorIssueResolutionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorIssueResolutionStatusRepository extends JpaRepository<VendorIssueResolutionStatus, UUID> {
    List<VendorIssueResolutionStatus> findByTenantId(UUID tenantId);
    Optional<VendorIssueResolutionStatus> findByTenantIdAndId(UUID tenantId, UUID id);
    List<VendorIssueResolutionStatus> findByTenantIdAndVendorKeyIssueId(UUID tenantId, UUID vendorKeyIssueId);
}