package com.pajakcodes.ecms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPermissions {

    private String username;
    private String password;
    private List<String> patterns;

}
