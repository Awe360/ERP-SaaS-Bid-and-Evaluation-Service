package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class VendorKeyIssueRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    @NotNull(message = "Supplier ID is required")
    private UUID supplierId;

    @NotBlank(message = "Supplier issue description is required")
    private String supplierIssue;

    @NotNull(message = "Date of claim is required")
    private LocalDate dateOfClaim;

    private String comment;
}