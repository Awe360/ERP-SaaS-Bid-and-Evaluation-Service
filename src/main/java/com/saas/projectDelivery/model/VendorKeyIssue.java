package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vendor_key_issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorKeyIssue extends Base {

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @Column(name = "supplier_id", nullable = false)
    private UUID supplierId;

    @Column(name = "supplier_issue", nullable = false, columnDefinition = "TEXT")
    private String supplierIssue;

    @Column(name = "date_of_claim", nullable = false)
    private LocalDate dateOfClaim;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_issue_document_id", foreignKey = @ForeignKey(name = "fk_vendor_issue_document"))
    private Document vendorIssueDocument;
}