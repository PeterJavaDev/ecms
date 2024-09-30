package com.pajakcodes.ecms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role_permission")
public class UserRolePermission {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "sys_permission_id", nullable = false)
    private SysPermission sysPermission;
}
