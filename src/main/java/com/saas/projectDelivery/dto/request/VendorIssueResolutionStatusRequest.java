package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class VendorIssueResolutionStatusRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    @NotNull(message = "Contractor ID is required")
    private UUID contractorId;

    @NotNull(message = "Vendor key issue ID is required")
    private UUID vendorKeyIssueId;

    @NotNull(message = "Resolution status ID is required")
    private UUID resolutionStatusId;

    private String remark;
}