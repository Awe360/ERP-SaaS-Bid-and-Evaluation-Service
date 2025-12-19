package com.saas.projectDelivery.dto.response.lookup;


import com.saas.projectDelivery.dto.response.BaseResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolutionStatusResponse extends BaseResponse {

    private String statusName;
}