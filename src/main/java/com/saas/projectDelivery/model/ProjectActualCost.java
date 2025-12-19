package com.saas.projectDelivery.model;

import com.saas.projectDelivery.model.lookup.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_actual_costs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectActualCost extends Base {

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @Column(name = "project_activity_id", nullable = false)
    private UUID projectActivityId;

    @Column(name = "actual_cost", nullable = false, precision = 18, scale = 2)
    private BigDecimal actualCost;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "currency_id", nullable = false, foreignKey = @ForeignKey(name = "fk_actual_costs_currency"))
    private Currency currency;

    @Column(name = "cost_date", nullable = false)
    private LocalDate costDate;

    private String remark;

    private String comment;
}