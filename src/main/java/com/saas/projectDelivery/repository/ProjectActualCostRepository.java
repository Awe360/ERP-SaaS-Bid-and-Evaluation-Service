package com.saas.projectDelivery.repository;

import com.saas.projectDelivery.model.ProjectActualCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectActualCostRepository extends JpaRepository<ProjectActualCost, UUID> {

    List<ProjectActualCost> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    List<ProjectActualCost> findByTenantIdAndProjectIdAndSubProjectId(UUID tenantId, UUID projectId, UUID subProjectId);

    List<ProjectActualCost> findByTenantIdAndProjectActivityId(UUID tenantId, UUID projectActivityId);

    @Query("SELECT pac FROM ProjectActualCost pac WHERE pac.tenantId = :tenantId " +
            "AND (:projectId IS NULL OR pac.projectId = :projectId) " +
            "AND (:activityId IS NULL OR pac.projectActivityId = :activityId) " +
            "AND (:startDate IS NULL OR pac.costDate >= :startDate) " +
            "AND (:endDate IS NULL OR pac.costDate <= :endDate)")
    List<ProjectActualCost> searchActualCosts(
            @Param("tenantId") UUID tenantId,
            @Param("projectId") UUID projectId,
            @Param("activityId") UUID projectActivityId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}