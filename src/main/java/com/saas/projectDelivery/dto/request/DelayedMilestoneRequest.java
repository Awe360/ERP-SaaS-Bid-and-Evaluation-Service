package com.saas.projectDelivery.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class DelayedMilestoneRequest {

    @NotEmpty(message = "At least one delayed milestone is required")
    @Valid
    private List<DelayedMilestoneItem> delayedMilestones;

    @Data
    public static class DelayedMilestoneItem {
        @NotNull private UUID projectId;
        @NotNull private UUID contractorId;
        @NotNull private UUID milestoneId;
        private String delayedPeriod;

        @NotBlank(message = "Delay reason is required")
        private String delayanceReason;

        @NotNull private LocalDate scheduleEndDate;

        private String actionTaken;
        private String description;
        private String comment;
    }
}