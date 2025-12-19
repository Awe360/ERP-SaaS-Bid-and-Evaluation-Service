package com.saas.projectDelivery.dto.clientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProjectDto {
    private UUID projectId;
    private String projectName;
    private String projectDescription;
}
