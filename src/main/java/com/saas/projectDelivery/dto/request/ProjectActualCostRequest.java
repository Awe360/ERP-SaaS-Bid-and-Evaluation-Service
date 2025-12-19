package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectActualCostRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    @NotNull(message = "Project Activity ID is required")
    private UUID projectActivityId;

    @NotNull(message = "Actual cost is required")
    @Positive(message = "Actual cost must be positive")
    private BigDecimal actualCost;

    @NotNull(message = "Currency Id is required")
    private UUID currencyId;

    @NotNull(message = "Cost date is required")
    @PastOrPresent(message = "Cost date cannot be in the future")
    private LocalDate costDate;

    private String remark;

    private String comment;
}