package com.saas.projectDelivery.dto.clientDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ActivityDto {
    private UUID activityId;
    private String activityName;
    private String description;
}
