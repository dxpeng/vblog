package com.xpit.vblog.mapper;

import com.xpit.vblog.entity.Role;

import java.util.List;

public interface RoleMapper {
    int addRole(Role role);

    List<Role> getRoleByUid(Long uid);

    int updateRoleById(Role role);

    int deleteRoleById(Long id);
}
