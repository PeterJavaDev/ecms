package com.pajakcodes.ecms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Validated
public record AddCallRecordRequest(

        @NotNull
        @Size(min = 1, max = 255)
        String title,

        @NotNull
        @Size(min = 1, max = 3000)
        String messageText,

        @NotNull
        @Range(min = 1, max = 4)
        Long incidentTypeId
) {
}
