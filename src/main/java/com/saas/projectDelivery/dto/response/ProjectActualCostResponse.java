package com.saas.projectDelivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectActualCostResponse extends BaseResponse {

    private UUID projectId;
    private UUID subProjectId;
    private UUID projectActivityId;
    private BigDecimal actualCost;
    private UUID currencyId;
    private LocalDate costDate;
    private String remark;
    private String comment;
}