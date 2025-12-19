
package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentOnExecutionResponse extends BaseResponse {
    private UUID projectId;
    private UUID subProjectId;
    private String accidentSite;
    private String accidentLocation;
    private UUID accidentTypeId;
    private LocalDate accidentDate;
    private LocalTime accidentTime;
    private String causeOfAccident;
    private String description;
    private boolean hasInsurance;
    private UUID insuranceCompanyId;
    private boolean treatmentStarted;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;
}