package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ActivityProgressSnapshotRequest {

    @NotNull(message = "Programme ID is required")
    private UUID programmeId;

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    @NotNull(message = "Activity ID is required")
    private UUID activityId;

    @NotNull(message = "Progress date is required")
    private LocalDate progressDate;

    @NotNull(message = "Current progress is required")
    @DecimalMin(value = "0.00", message = "Progress must be at least 0.00")
    @DecimalMax(value = "100.00", message = "Progress must not exceed 100.00")
    private BigDecimal currentProgress;

    @NotBlank(message = "Critical field is required")
    private String critical;

    private String description;
}