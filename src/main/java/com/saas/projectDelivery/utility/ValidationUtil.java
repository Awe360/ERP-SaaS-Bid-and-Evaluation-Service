package com.saas.projectDelivery.utility;

import com.saas.projectDelivery.exception.ResourceNotFoundException;
import com.saas.projectDelivery.model.*;
import com.saas.projectDelivery.model.lookup.AccidentType;
import com.saas.projectDelivery.model.lookup.Currency;
import com.saas.projectDelivery.repository.*;
import com.saas.projectDelivery.repository.lookup.AccidentTypeRepository;
import com.saas.projectDelivery.repository.lookup.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final CurrencyRepository currencyRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final ActivityProgressSnapshotRepository activityProgressSnapshotRepository;
    private final ProjectClosingRepository projectClosingRepository;
    private final VendorKeyIssueRepository vendorKeyIssueRepository;
    private final VendorIssueResolutionStatusRepository vendorIssueResolutionStatusRepository;
    private final AccidentOnExecutionRepository accidentOnExecutionRepository;
    private final DelayedActivityRepository delayedActivityRepository;
    private final DelayedMilestoneRepository delayedMilestoneRepository;


    // private final ProjectServiceClient projectClient;
    // private final ActivityServiceClient activityClient;

    public void validateProjectExists(UUID tenantId, UUID projectId) {
        // Call project-initiation-service via Feign to verify project exists and belongs to tenant
        // throw ResourceNotFoundException if not
    }


    public void validateProjectActivityExists(UUID tenantId, UUID activityId) {
        // Call project-initiation-service to verify activity exists under the project
    }

    public Currency getCurrencyById(UUID tenantId, UUID currencyId) {

        return currencyRepository
                .findByTenantIdAndIdAndActiveTrue(tenantId, currencyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currency not found or inactive with id '" + currencyId + "'"));
    }

    public AccidentType getAccidentTypeById(UUID tenantId, UUID accidentTypeId) {

        return accidentTypeRepository
                .findByTenantIdAndId(tenantId, accidentTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Accident Type not found  with id '" + accidentTypeId + "'"));
    }

    public ActivityProgressSnapshot findProgressByTenantIdAndId(UUID tenantId, UUID id) {
        return activityProgressSnapshotRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Progress snapshot not found with id '" + id + "'"));
    }


    public ProjectClosing findProjectClosingByTenantIdAndId(UUID tenantId, UUID id) {
        return projectClosingRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project closing record not found with id '" + id + "'"));
    }

    public VendorKeyIssue findVendorKeyIssueByTenantIdAndId(UUID tenantId, UUID id) {
        return vendorKeyIssueRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor key issue not found with id '" + id + "'"));
    }

    public VendorIssueResolutionStatus findResolutionStatusByTenantIdAndId(UUID tenantId, UUID id) {
        return vendorIssueResolutionStatusRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution status update not found with id '" + id + "'"));
    }

    public AccidentOnExecution findAccidentByTenantIdAndId(UUID tenantId, UUID id) {
        return accidentOnExecutionRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Accident not found with id '" + id + "'"));
    }

    public DelayedActivity findDelayedActivityByTenantIdAndId(UUID tenantId, UUID id) {
        return delayedActivityRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Delayed activity not found with id '" + id + "'"));
    }

    public DelayedMilestone findDelayedMilestoneByTenantIdAndId(UUID tenantId, UUID id) {
        return delayedMilestoneRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Delayed milestone not found with id '" + id + "'"));
    }


}