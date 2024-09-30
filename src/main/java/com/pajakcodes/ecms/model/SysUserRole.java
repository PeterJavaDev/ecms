package com.pajakcodes.ecms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SysUserRole {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sys_user_id", nullable = false)
    private SysUser sysUser;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;
}
