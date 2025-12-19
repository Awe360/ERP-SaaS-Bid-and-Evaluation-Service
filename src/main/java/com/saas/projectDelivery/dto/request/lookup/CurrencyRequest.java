package com.saas.projectDelivery.dto.request.lookup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CurrencyRequest {

    @NotBlank(message = "Currency code is required")
    @Size(max = 10)
    private String currencyCode;

    @NotBlank(message = "Currency name is required")
    @Size(max = 100)
    private String currencyName;

    @Size(max = 10)
    private String symbol;

    private boolean active = true;
}
