package com.saas.projectDelivery.model;

import com.saas.projectDelivery.model.lookup.ResolutionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vendor_issue_resolution_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorIssueResolutionStatus extends Base {

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @Column(name = "contractor_id", nullable = false)
    private UUID contractorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_key_issue_id", nullable = false)
    private VendorKeyIssue vendorKeyIssue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolution_status_id", nullable = false)
    private ResolutionStatus resolutionStatus;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "resolution_document_id", foreignKey = @ForeignKey(name = "fk_resolution_document"))
    private Document resolutionDocument;
}