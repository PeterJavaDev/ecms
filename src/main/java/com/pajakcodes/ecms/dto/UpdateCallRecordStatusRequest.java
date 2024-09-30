package com.pajakcodes.ecms.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Validated
public record UpdateCallRecordStatusRequest(

        @NotNull
        Long callRecordId,

        @NotNull
        @Range(min = 1, max = 3)
        Long callStatusTypeId
) {
}
