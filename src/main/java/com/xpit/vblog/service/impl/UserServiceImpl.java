package com.xpit.vblog.service.impl;

import com.xpit.vblog.common.utils.Util;
import com.xpit.vblog.entity.Role;
import com.xpit.vblog.entity.User;
import com.xpit.vblog.mapper.RoleMapper;
import com.xpit.vblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roleList = roleMapper.getRoleByUid(user.getId());
        user.setRoles(roleList);
        return user;
    }

    /**
     * 用户注册
     * 0成功 1用户名重复 2失败
     */
    public int reg(User user) {
        User loadUserByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        //插入用户，插入之前先对密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setEnabled(true);
        long result = userMapper.reg(user);
        //配置用户角色，默认为普通用户
        String[] roles = new String[]{"2"};
        int i = roleMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }

    public List<User> getUserByNickname(String nickname) {
        return userMapper.getUserByNickname(nickname);
    }

    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }

    public int updateUserByEnabled(Boolean enabled, Long uid) {
        return userMapper.updateUserByEnabled(enabled, uid);
    }

    public int deleteUserById(Long uid) {
        return userMapper.deleteUserById(uid);
    }

    public int updateUserRoles(Long[] rids, Long id) {
        int i = userMapper.deleteUserRoleByUid(id);
        return userMapper.setUserRole(rids, id);
    }

    public User getUserBId(Long id) {
        return userMapper.getUserBId(id);
    }

}
