package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProjectClosingRequest {

    @NotNull(message = "Programme ID is required")
    private UUID programmeId;

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private String projectStatus;

    @NotNull(message = "Please specify if project is closed")
    private Boolean projectClosed;

    private LocalDate closedDate;

    private BigDecimal projectEstimateCost;

    private BigDecimal projectActualCost;

    private String remark;
}