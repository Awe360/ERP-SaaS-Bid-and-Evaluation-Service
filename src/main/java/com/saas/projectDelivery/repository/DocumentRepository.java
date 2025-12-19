
package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<Document> findByTenantIdAndId(UUID tenantId, UUID id);

}