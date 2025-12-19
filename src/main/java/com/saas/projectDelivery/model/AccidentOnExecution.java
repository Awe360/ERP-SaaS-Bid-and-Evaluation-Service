
package com.saas.projectDelivery.model;

import com.saas.projectDelivery.model.lookup.AccidentType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accident_on_executions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentOnExecution extends Base {

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    private String accidentSite;

    @Column(nullable = false)
    private String accidentLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_accident_type"))
    private AccidentType accidentType;

    @Column(nullable = false)
    private LocalDate accidentDate;

    private LocalTime accidentTime;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String causeOfAccident;

    private String description;
    private boolean hasInsurance;
    private UUID insuranceCompanyId;
    private boolean treatmentStarted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id", foreignKey = @ForeignKey(name = "fk_accident_attachment_document"))
    private Document attachmentDocument;
}