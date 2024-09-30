package com.pajakcodes.ecms.repository;

import com.pajakcodes.ecms.model.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepository extends CrudRepository<SysUser, Long> {

    SysUser getSysUserByUsername(String username);

    @Query("""
                SELECT DISTINCT p.name
                FROM SysUser u
                JOIN SysUserRole ur ON u.id = ur.sysUser.id
                JOIN UserRole r ON ur.userRole.id = r.id
                JOIN UserRolePermission rp ON r.id = rp.userRole.id
                JOIN SysPermission p ON rp.sysPermission.id = p.id
                WHERE u.username = :username
            """)
    List<String> findPermissionByUsername(@Param("username") String username);

}
