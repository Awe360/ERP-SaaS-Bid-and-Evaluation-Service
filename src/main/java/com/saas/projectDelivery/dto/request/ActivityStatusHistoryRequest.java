package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ActivityStatusHistoryRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    @NotNull(message = "Activity ID is required")
    private UUID activityId;

    @NotNull(message = "Old status is required")
    private String oldStatus;

    @NotNull(message = "New status is required")
    private String newStatus;

    private String comment;
}