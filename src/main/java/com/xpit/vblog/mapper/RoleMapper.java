package com.xpit.vblog.mapper;

import com.xpit.vblog.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int addRole(Role role);

    List<Role> getRoleByUid(Long uid);

    int updateRoleById(Role role);

    int deleteRoleById(Long id);

    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);
}
