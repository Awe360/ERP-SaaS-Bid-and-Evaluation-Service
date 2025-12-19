package com.saas.projectDelivery.dto.request.lookup;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusRequest {
    @NotNull(message = "Active status is required")
    private boolean active;
}