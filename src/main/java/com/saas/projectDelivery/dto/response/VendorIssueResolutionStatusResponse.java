package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorIssueResolutionStatusResponse extends BaseResponse {

    private UUID projectId;
    private UUID subProjectId;
    private UUID contractorId;

    private UUID vendorKeyIssueId;
    private UUID resolutionStatusId;
    private String resolutionStatusName;

    private String remark;

    private UUID resolutionDocumentId;
    private String resolutionFileName;
    private String resolutionFileType;
    private Long resolutionFileSize;
}