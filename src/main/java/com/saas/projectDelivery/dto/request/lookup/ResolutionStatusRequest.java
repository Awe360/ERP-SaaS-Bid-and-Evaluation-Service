package com.saas.projectDelivery.dto.request.lookup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResolutionStatusRequest {

    @NotBlank(message = "Status name is required")
    @Size(max = 100, message = "Status name must not exceed 100 characters")
    private String statusName;
}