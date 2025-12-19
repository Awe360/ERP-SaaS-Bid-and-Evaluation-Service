package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activity_progress_snapshots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityProgressSnapshot extends Base {

    @Column(name = "programme_id", nullable = false)
    private UUID programmeId;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @Column(name = "activity_id", nullable = false)
    private UUID activityId;

    @Column(name = "progress_date", nullable = false)
    private LocalDate progressDate;

    @Column(name = "current_progress", nullable = false, precision = 5, scale = 2)
    private BigDecimal currentProgress;

    @Column(name = "critical", nullable = false)
    private String critical;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_progress_document_id", foreignKey = @ForeignKey(name = "fk_progress_document"))
    private Document activityProgressDocument;
}