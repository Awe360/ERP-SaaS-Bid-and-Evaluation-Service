package com.saas.projectDelivery.dto.response.lookup;


import com.saas.projectDelivery.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentTypeResponse extends BaseResponse {

    private String typeName;

}