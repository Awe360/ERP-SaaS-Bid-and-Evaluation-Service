package com.saas.projectDelivery.enums;

import lombok.Getter;

@Getter
public enum ResourceName {

    /* Resource */
    GET_ALL_RESOURCES("Get All Resources"),
    GET_RESOURCES_BY_ROLE_NAME("Get Resources by Role"),
    GET_RESOURCE_BY_ID("Get Resource Details"),
    GET_RESOURCE_BY_NAME("Get Resource by Name"),
    GRANT_RESOURCE_ACCESS_TO_ROLE("Grant Resource Access to Role"),
    REVOKE_RESOURCE_ACCESS_FROM_ROLE("Revoke Resource Access from Role"),

    /* Accident on Execution */
    ADD_ACCIDENT_ON_EXECUTION("Add Accident on Execution"),
    GET_ALL_ACCIDENTS_ON_EXECUTION("Get All Accidents on Execution"),
    GET_ACCIDENT_ON_EXECUTION_BY_ID("Get Accident on Execution Details"),
    UPDATE_ACCIDENT_ON_EXECUTION("Update Accident on Execution"),
    DELETE_ACCIDENT_ON_EXECUTION("Delete Accident on Execution"),
    DOWNLOAD_ACCIDENT_ATTACHMENT("Download Accident Attachment"),

    /* Activity Progress Snapshot */
    ADD_ACTIVITY_PROGRESS_SNAPSHOT("Add Activity Progress Snapshot"),
    GET_ALL_ACTIVITY_PROGRESS_SNAPSHOTS("Get All Activity Progress Snapshots"),
    GET_ACTIVITY_PROGRESS_SNAPSHOT_BY_ID("Get Activity Progress Snapshot Details"),
    UPDATE_ACTIVITY_PROGRESS_SNAPSHOT("Update Activity Progress Snapshot"),
    DELETE_ACTIVITY_PROGRESS_SNAPSHOT("Delete Activity Progress Snapshot"),
    DOWNLOAD_ACTIVITY_PROGRESS_ATTACHMENT("Download Activity Progress Attachment"),

    /* Activity Status History */
    ADD_ACTIVITY_STATUS_HISTORY("Add Activity Status History"),
    GET_ALL_ACTIVITY_STATUS_HISTORIES("Get All Activity Status Histories"),
    GET_ACTIVITY_STATUS_HISTORY_BY_PROJECT("Get Activity Status History by Project"),
    GET_ACTIVITY_STATUS_HISTORY_BY_ACTIVITY("Get Activity Status History by Activity"),
    GET_ACTIVITY_STATUS_HISTORY_BY_ID("Get Activity Status History Details"),

    /* Delayed Activity */
    ADD_DELAYED_ACTIVITY("Add Delayed Activity"),
    GET_DELAYED_ACTIVITY_BY_ID("Get Delayed Activity Details"),
    GET_DELAYED_ACTIVITIES_BY_PROJECT("Get Delayed Activities by Project"),
    GET_DELAYED_ACTIVITIES_BY_PROJECT_AND_MILESTONE("Get Delayed Activities by Project and Milestone"),
    UPDATE_DELAYED_ACTIVITY("Update Delayed Activity"),
    DELETE_DELAYED_ACTIVITY("Delete Delayed Activity"),

    /* Delayed Milestone */
    ADD_DELAYED_MILESTONE("Add Delayed Milestone"),
    GET_DELAYED_MILESTONE_BY_ID("Get Delayed Milestone Details"),
    GET_DELAYED_MILESTONES_BY_PROJECT("Get Delayed Milestones by Project"),
    GET_DELAYED_MILESTONES_BY_PROJECT_AND_MILESTONE("Get Delayed Milestones by Project and Milestone"),
    UPDATE_DELAYED_MILESTONE("Update Delayed Milestone"),
    DELETE_DELAYED_MILESTONE("Delete Delayed Milestone"),

    /* Project Actual Cost */
    ADD_PROJECT_ACTUAL_COST("Add Project Actual Cost"),
    GET_PROJECT_ACTUAL_COSTS_BY_PROJECT("Get Actual Costs by Project"),
    GET_PROJECT_ACTUAL_COSTS_BY_ACTIVITY("Get Actual Costs by Activity"),
    SEARCH_PROJECT_ACTUAL_COSTS("Search Project Actual Costs"),
    GET_PROJECT_ACTUAL_COST_BY_ID("Get Actual Cost Details"),
    UPDATE_PROJECT_ACTUAL_COST("Update Project Actual Cost"),
    DELETE_PROJECT_ACTUAL_COST("Delete Project Actual Cost"),

    /* Project Closing */
    ADD_PROJECT_CLOSING("Add Project Closing"),
    GET_ALL_PROJECT_CLOSINGS("Get All Project Closings"),
    GET_PROJECT_CLOSING_BY_ID("Get Project Closing Details"),
    GET_PROJECT_CLOSING_BY_PROJECT_ID("Get Project Closing by Project"),
    UPDATE_PROJECT_CLOSING("Update Project Closing"),
    DELETE_PROJECT_CLOSING("Delete Project Closing"),
    DOWNLOAD_PROJECT_CLOSING_DOCUMENT("Download Project Closing Document"),

    /* Project Status History */
    ADD_PROJECT_STATUS_HISTORY("Add Project Status History"),
    GET_ALL_PROJECT_STATUS_HISTORIES("Get All Project Status Histories"),
    GET_PROJECT_STATUS_HISTORY_BY_PROJECT("Get Project Status History by Project"),
    GET_PROJECT_STATUS_HISTORY_BY_ID("Get Project Status History Details"),

    /* Release Project Resources */
    RELEASE_PROJECT_RESOURCES("Release Project Resources"),

    /* Vendor Key Issue */
    ADD_VENDOR_KEY_ISSUE("Add Vendor Key Issue"),
    GET_ALL_VENDOR_KEY_ISSUES("Get All Vendor Key Issues"),
    GET_VENDOR_KEY_ISSUE_BY_ID("Get Vendor Key Issue Details"),
    UPDATE_VENDOR_KEY_ISSUE("Update Vendor Key Issue"),
    DELETE_VENDOR_KEY_ISSUE("Delete Vendor Key Issue"),
    DOWNLOAD_VENDOR_ISSUE_ATTACHMENT("Download Vendor Issue Attachment"),

    /* Vendor Issue Resolution Status */
    ADD_VENDOR_ISSUE_RESOLUTION("Add Vendor Issue Resolution Status"),
    UPDATE_VENDOR_ISSUE_RESOLUTION("Update Vendor Issue Resolution Status"),
    GET_ALL_VENDOR_ISSUE_RESOLUTIONS("Get All Vendor Issue Resolutions"),
    GET_VENDOR_ISSUE_RESOLUTIONS_BY_ISSUE("Get Vendor Issue Resolutions by Issue"),
    GET_VENDOR_ISSUE_RESOLUTION_BY_ID("Get Vendor Issue Resolution Details"),
    DOWNLOAD_VENDOR_RESOLUTION_ATTACHMENT("Download Vendor Resolution Attachment"),

    /* Lookup - Accident Type */
    ADD_ACCIDENT_TYPE("Add Accident Type"),
    GET_ALL_ACCIDENT_TYPES("Get All Accident Types"),
    GET_ACCIDENT_TYPE_BY_ID("Get Accident Type Details"),
    UPDATE_ACCIDENT_TYPE("Update Accident Type"),
    DELETE_ACCIDENT_TYPE("Delete Accident Type"),

    /* Lookup - Currency */
    ADD_CURRENCY("Add Currency"),
    GET_ALL_CURRENCIES("Get All Currencies"),
    GET_ALL_ACTIVE_CURRENCIES("Get All Active Currencies"),
    GET_CURRENCY_BY_ID("Get Currency Details"),
    UPDATE_CURRENCY("Update Currency"),
    DELETE_CURRENCY("Delete Currency"),
    CHANGE_CURRENCY_STATUS("Change Currency Status"),

    /* Lookup - Resolution Status */
    ADD_RESOLUTION_STATUS("Add Resolution Status"),
    GET_ALL_RESOLUTION_STATUSES("Get All Resolution Statuses"),
    GET_RESOLUTION_STATUS_BY_ID("Get Resolution Status Details"),
    UPDATE_RESOLUTION_STATUS("Update Resolution Status"),
    DELETE_RESOLUTION_STATUS("Delete Resolution Status");

    private final String value;

    ResourceName(String value) {
        this.value = value;
    }
}