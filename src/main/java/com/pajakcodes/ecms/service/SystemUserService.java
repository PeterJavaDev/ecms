package com.pajakcodes.ecms.service;

import com.pajakcodes.ecms.model.SysUser;
import com.pajakcodes.ecms.repository.SysUserRepository;
import com.pajakcodes.ecms.dto.SystemUser;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SystemUserService {

    private static final Logger log = LogManager.getLogger(SystemUserService.class);

    private final SysUserRepository sysUserRepository;

    public SystemUser findByUsername(String username) {
        SysUser sysUser = sysUserRepository.getSysUserByUsername(username);
        if (sysUser == null) {
            return null;
        }
        List<String> permissions = sysUserRepository.findPermissionByUsername(username);

        log.debug("SystemUserService - Users permissions: " + permissions);

        return SystemUser.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .permissions(permissions)
                .build();
    }

}
