package com.saas.projectDelivery.repository;


import com.saas.projectDelivery.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findByTenantIdAndId(UUID tenantId, UUID projectId);
}
