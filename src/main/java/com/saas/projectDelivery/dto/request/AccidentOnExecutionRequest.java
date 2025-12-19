
package com.saas.projectDelivery.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class AccidentOnExecutionRequest {

    @NotNull(message = "Project ID is required")
    private UUID projectId;

    private UUID subProjectId;

    private String accidentSite;

    @NotBlank(message = "Accident location is required")
    private String accidentLocation;

    @NotNull(message = "Accident type Id is required")
    private UUID accidentTypeId;

    @NotNull(message = "Accident date is required")
    private LocalDate accidentDate;

    private LocalTime accidentTime;

    @NotBlank(message = "Cause of accident is required")
    private String causeOfAccident;

    private String description;

    private boolean hasInsurance = false;
    private UUID insuranceCompanyId;
    private boolean treatmentStarted = false;
}