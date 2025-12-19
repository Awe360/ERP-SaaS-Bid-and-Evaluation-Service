package com.saas.projectDelivery.utility;

import com.saas.projectDelivery.enums.ResourceName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionEvaluator {

    private final PermissionUtil permissionUtil;

    public void getAllResourcesPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_ALL_RESOURCES);
        }
    }

    public void getResourcesByRoleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RESOURCES_BY_ROLE_NAME);
    }

    public void getResourceByIdPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_RESOURCE_BY_ID);
        }
    }

    public void getResourceByNamePermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            permissionUtil.isTenantUser(tenantId);
        }
    }

    public void grantResourceAccessToRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE);
    }

    public void revokeResourceAccessFromRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE);
    }

    public void addAccidentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ACCIDENT_ON_EXECUTION);
    }

    public void getAllAccidentsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACCIDENTS_ON_EXECUTION);
    }

    public void getAccidentByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACCIDENT_ON_EXECUTION_BY_ID);
    }

    public void updateAccidentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_ACCIDENT_ON_EXECUTION);
    }

    public void deleteAccidentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_ACCIDENT_ON_EXECUTION);
    }

    public void downloadAccidentAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_ACCIDENT_ATTACHMENT);
    }

    public void addProgressSnapshotPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ACTIVITY_PROGRESS_SNAPSHOT);
    }

    public void getAllProgressSnapshotsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACTIVITY_PROGRESS_SNAPSHOTS);
    }

    public void getProgressSnapshotByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITY_PROGRESS_SNAPSHOT_BY_ID);
    }

    public void updateProgressSnapshotPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_ACTIVITY_PROGRESS_SNAPSHOT);
    }

    public void deleteProgressSnapshotPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_ACTIVITY_PROGRESS_SNAPSHOT);
    }

    public void downloadProgressAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_ACTIVITY_PROGRESS_ATTACHMENT);
    }

    public void addActivityStatusHistoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ACTIVITY_STATUS_HISTORY);
    }

    public void getAllActivityStatusHistoriesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACTIVITY_STATUS_HISTORIES);
    }

    public void getActivityStatusHistoryByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_PROJECT);
    }

    public void getActivityStatusHistoryByActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_ACTIVITY);
    }

    public void getActivityStatusHistoryByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_ID);
    }

    public void addDelayedActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DELAYED_ACTIVITY);
    }

    public void getDelayedActivityByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DELAYED_ACTIVITY_BY_ID);
    }

    public void getDelayedActivitiesByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DELAYED_ACTIVITIES_BY_PROJECT);
    }

    public void updateDelayedActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DELAYED_ACTIVITY);
    }

    public void deleteDelayedActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DELAYED_ACTIVITY);
    }

    public void addDelayedMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DELAYED_MILESTONE);
    }

    public void getDelayedMilestoneByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DELAYED_MILESTONE_BY_ID);
    }

    public void getDelayedMilestonesByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DELAYED_MILESTONES_BY_PROJECT);
    }

    public void updateDelayedMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DELAYED_MILESTONE);
    }

    public void deleteDelayedMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DELAYED_MILESTONE);
    }

    public void addActualCostPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_ACTUAL_COST);
    }

    public void getActualCostsByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_ACTUAL_COSTS_BY_PROJECT);
    }

    public void getActualCostsByActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_ACTUAL_COSTS_BY_ACTIVITY);
    }

    public void searchActualCostsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.SEARCH_PROJECT_ACTUAL_COSTS);
    }

    public void getActualCostByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_ACTUAL_COST_BY_ID);
    }

    public void updateActualCostPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT_ACTUAL_COST);
    }

    public void deleteActualCostPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT_ACTUAL_COST);
    }

    public void addProjectClosingPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_CLOSING);
    }

    public void getAllProjectClosingsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECT_CLOSINGS);
    }

    public void getProjectClosingByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_CLOSING_BY_ID);
    }

    public void getProjectClosingByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_CLOSING_BY_PROJECT_ID);
    }

    public void updateProjectClosingPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT_CLOSING);
    }

    public void deleteProjectClosingPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT_CLOSING);
    }

    public void downloadProjectClosingDocumentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_PROJECT_CLOSING_DOCUMENT);
    }

    public void addProjectStatusHistoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_STATUS_HISTORY);
    }

    public void getAllProjectStatusHistoriesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECT_STATUS_HISTORIES);
    }

    public void getProjectStatusHistoryByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_STATUS_HISTORY_BY_PROJECT);
    }

    public void getProjectStatusHistoryByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_STATUS_HISTORY_BY_ID);
    }

    public void releaseProjectResourcesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.RELEASE_PROJECT_RESOURCES);
    }

    public void addVendorKeyIssuePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_VENDOR_KEY_ISSUE);
    }

    public void getAllVendorKeyIssuesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_VENDOR_KEY_ISSUES);
    }

    public void getVendorKeyIssueByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_VENDOR_KEY_ISSUE_BY_ID);
    }

    public void updateVendorKeyIssuePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_VENDOR_KEY_ISSUE);
    }

    public void deleteVendorKeyIssuePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_VENDOR_KEY_ISSUE);
    }

    public void downloadVendorIssueAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_VENDOR_ISSUE_ATTACHMENT);
    }

    public void addVendorResolutionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_VENDOR_ISSUE_RESOLUTION);
    }

    public void updateVendorResolutionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_VENDOR_ISSUE_RESOLUTION);
    }

    public void getAllVendorResolutionsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_VENDOR_ISSUE_RESOLUTIONS);
    }

    public void getVendorResolutionsByIssuePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_VENDOR_ISSUE_RESOLUTIONS_BY_ISSUE);
    }

    public void getVendorResolutionByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_VENDOR_ISSUE_RESOLUTION_BY_ID);
    }

    public void downloadVendorResolutionAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_VENDOR_RESOLUTION_ATTACHMENT);
    }

    public void addAccidentTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ACCIDENT_TYPE);
    }

    public void getAllAccidentTypesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACCIDENT_TYPES);
    }

    public void getAccidentTypeByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACCIDENT_TYPE_BY_ID);
    }

    public void updateAccidentTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_ACCIDENT_TYPE);
    }

    public void deleteAccidentTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_ACCIDENT_TYPE);
    }

    public void addCurrencyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_CURRENCY);
    }

    public void getAllCurrenciesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_CURRENCIES);
    }

    public void getAllActiveCurrenciesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACTIVE_CURRENCIES);
    }

    public void getCurrencyByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_CURRENCY_BY_ID);
    }

    public void updateCurrencyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_CURRENCY);
    }

    public void deleteCurrencyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_CURRENCY);
    }

    public void changeCurrencyStatusPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.CHANGE_CURRENCY_STATUS);
    }

    public void addResolutionStatusPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_RESOLUTION_STATUS);
    }

    public void getAllResolutionStatusesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_RESOLUTION_STATUSES);
    }

    public void getResolutionStatusByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RESOLUTION_STATUS_BY_ID);
    }

    public void updateResolutionStatusPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_RESOLUTION_STATUS);
    }

    public void deleteResolutionStatusPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_RESOLUTION_STATUS);
    }

    private void checkPermission(UUID tenantId, ResourceName resourceName) {
        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}