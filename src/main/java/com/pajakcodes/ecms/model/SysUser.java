package com.pajakcodes.ecms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class SysUser {

    @Id
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "sysUser", cascade = CascadeType.ALL)
    private List<SysUserRole> userRoles;
}
