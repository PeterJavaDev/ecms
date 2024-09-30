package com.pajakcodes.ecms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Validated
public class SystemUser {

    @NotNull
    @Size(min = 2, max = 20)
    private String username;

    @NotNull
    @Size(min = 5, max = 20)
    private String password;

    private List<String> permissions;

}
