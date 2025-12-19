package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "delayed_activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayedActivity extends Base {
    @Column(name = "project_id", nullable = false)
    private UUID projectId;
    @Column(name = "contractor_id", nullable = false)
    private UUID contractorId;
    @Column(name = "delayed_activity_id", nullable = false)
    private UUID delayedActivityId;
    @Column(name = "delayed_period", length = 100)
    private String delayedPeriod;
    @Column(name = "delayance_reason", nullable = false, columnDefinition = "TEXT")
    private String delayanceReason;
    @Column(name = "schedule_end_date", nullable = false)
    private LocalDate scheduleEndDate;
    @Column(name = "action_taken", columnDefinition = "TEXT")
    private String actionTaken;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}