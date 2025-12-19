package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ProjectStatusHistoryRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    @NotBlank(message = "Previous status is required")
    @Size(max = 50)
    private String previousStatus;

    @NotBlank(message = "New status is required")
    @Size(max = 50)
    private String newStatus;

    @Size(max = 4000)
    private String comment;
}