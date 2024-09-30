package com.pajakcodes.ecms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRole {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<SysUserRole> userRoles;

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<UserRolePermission> rolePermissions;
}