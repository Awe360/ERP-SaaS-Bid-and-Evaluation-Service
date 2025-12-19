package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorKeyIssueResponse extends BaseResponse {

    private UUID projectId;
    private UUID subProjectId;
    private UUID supplierId;
    private String supplierIssue;
    private LocalDate dateOfClaim;
    private String comment;

    private UUID vendorIssueDocumentId;
    private String vendorIssueFileName;
    private String vendorIssueFileType;
    private Long vendorIssueFileSize;
}