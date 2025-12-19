package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClosingResponse extends BaseResponse {

    private UUID programmeId;
    private UUID projectId;
    private String projectStatus;
    private boolean projectClosed;
    private LocalDate closedDate;
    private BigDecimal projectEstimateCost;
    private BigDecimal projectActualCost;
    private String remark;

    private UUID closingDocumentId;
    private String closingFileName;
    private String closingFileType;
    private Long closingFileSize;
}