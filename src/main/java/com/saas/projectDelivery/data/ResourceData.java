package com.saas.projectDelivery.data;

import com.saas.projectDelivery.dto.eventDto.ResourceEvent;
import com.saas.projectDelivery.enums.ResourceName;
import com.saas.projectDelivery.mapper.ResourceMapper;
import com.saas.projectDelivery.model.Resource;
import com.saas.projectDelivery.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ResourceData {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public void saveResource(ResourceEvent eventResponse) {

        Set<Resource> resources = new HashSet<>();

        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_RESOURCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCES_BY_ROLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCE_BY_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ACCIDENT_ON_EXECUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACCIDENTS_ON_EXECUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACCIDENT_ON_EXECUTION_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_ACCIDENT_ON_EXECUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_ACCIDENT_ON_EXECUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_ACCIDENT_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ACTIVITY_PROGRESS_SNAPSHOT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACTIVITY_PROGRESS_SNAPSHOTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITY_PROGRESS_SNAPSHOT_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_ACTIVITY_PROGRESS_SNAPSHOT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_ACTIVITY_PROGRESS_SNAPSHOT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_ACTIVITY_PROGRESS_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ACTIVITY_STATUS_HISTORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACTIVITY_STATUS_HISTORIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITY_STATUS_HISTORY_BY_ID.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_DELAYED_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_ACTIVITY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_ACTIVITIES_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_ACTIVITIES_BY_PROJECT_AND_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_DELAYED_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_DELAYED_ACTIVITY.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_DELAYED_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_MILESTONE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_MILESTONES_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_DELAYED_MILESTONES_BY_PROJECT_AND_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_DELAYED_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_DELAYED_MILESTONE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_ACTUAL_COST.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_ACTUAL_COSTS_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_ACTUAL_COSTS_BY_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.SEARCH_PROJECT_ACTUAL_COSTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_ACTUAL_COST_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT_ACTUAL_COST.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT_ACTUAL_COST.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_CLOSING.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECT_CLOSINGS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_CLOSING_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_CLOSING_BY_PROJECT_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT_CLOSING.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT_CLOSING.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_PROJECT_CLOSING_DOCUMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_STATUS_HISTORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECT_STATUS_HISTORIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_STATUS_HISTORY_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_STATUS_HISTORY_BY_ID.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.RELEASE_PROJECT_RESOURCES.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_VENDOR_KEY_ISSUE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_VENDOR_KEY_ISSUES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_VENDOR_KEY_ISSUE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_VENDOR_KEY_ISSUE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_VENDOR_KEY_ISSUE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_VENDOR_ISSUE_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_VENDOR_ISSUE_RESOLUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_VENDOR_ISSUE_RESOLUTION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_VENDOR_ISSUE_RESOLUTIONS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_VENDOR_ISSUE_RESOLUTIONS_BY_ISSUE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_VENDOR_ISSUE_RESOLUTION_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_VENDOR_RESOLUTION_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ACCIDENT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACCIDENT_TYPES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACCIDENT_TYPE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_ACCIDENT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_ACCIDENT_TYPE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_CURRENCY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_CURRENCIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACTIVE_CURRENCIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_CURRENCY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_CURRENCY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_CURRENCY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.CHANGE_CURRENCY_STATUS.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_RESOLUTION_STATUS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_RESOLUTION_STATUSES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOLUTION_STATUS_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_RESOLUTION_STATUS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_RESOLUTION_STATUS.getValue(), null, eventResponse));

        List<Resource> existedResources = resourceRepository.findByTenantId(eventResponse.getTenantId());
        resourceRepository.deleteAll(existedResources);
        resourceRepository.saveAll(resources);
    }
}