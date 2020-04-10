package com.xpit.vblog.service.impl;

import com.xpit.vblog.entity.Role;
import com.xpit.vblog.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {
    @Autowired
    private RoleMapper roleMapper;

    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    public int addRoles(String[] rids, Long uid) {
        return addRoles(rids, uid);
    }

    public List<Role> getRoleByUid(Long id) {
        return roleMapper.getRoleByUid(id);
    }

    public int updateRoleById(Role role) {
        return roleMapper.updateRoleById(role);
    }

    public int deleteRoleById(Long id) {
        return roleMapper.deleteRoleById(id);
    }
}
