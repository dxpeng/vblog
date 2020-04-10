package com.xpit.vblog.mapper;


import com.xpit.vblog.entity.Role;
import com.xpit.vblog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    User loadUserByUsername(@Param("username") String username);

    long reg(User user);

    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    List<User> getUserByNickname(@Param("nickname") String nickname);

    List<Role> getAllRole();

    int updateUserByEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

    int deleteUserById(Long uid);

    int deleteUserRoleByUid(Long id);

    int setUserRole(@Param("rids") Long[] rids,@Param("id") Long id);

    User getUserBId(@Param("id") Long id);
}
