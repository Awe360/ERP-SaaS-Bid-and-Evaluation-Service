package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_closings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClosing extends Base {

    @Column(name = "programme_id", nullable = false)
    private UUID programmeId;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "project_status", length = 100)
    private String projectStatus;

    @Column(name = "is_project_closed", nullable = false)
    private boolean projectClosed;

    @Column(name = "closed_date")
    private LocalDate closedDate;

    @Column(name = "project_estimate_cost", precision = 15, scale = 2)
    private BigDecimal projectEstimateCost;

    @Column(name = "project_actual_cost", precision = 15, scale = 2)
    private BigDecimal projectActualCost;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "closing_document_id", foreignKey = @ForeignKey(name = "fk_project_closing_document"))
    private Document closingDocument;
}