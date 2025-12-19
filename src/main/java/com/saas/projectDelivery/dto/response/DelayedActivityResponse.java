package com.saas.projectDelivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayedActivityResponse extends BaseResponse {
    private UUID projectId;
    private UUID contractorId;
    private UUID delayedActivityId;
    private String delayedPeriod;
    private String delayanceReason;
    private LocalDate scheduleEndDate;
    private String actionTaken;
    private String description;
    private String comment;
}