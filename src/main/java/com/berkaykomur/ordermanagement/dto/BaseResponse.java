package com.berkaykomur.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse {

    private Long id;
    private LocalDateTime  createdDate;
    private Boolean isActive;
}
