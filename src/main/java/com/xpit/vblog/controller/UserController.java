package com.xpit.vblog.controller;

import com.xpit.vblog.common.utils.Util;
import com.xpit.vblog.common.vo.RespVO;
import com.xpit.vblog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/currentUserName")
    public String currentUserName() {
        return Util.getCurrentUser().getUsername();
    }

    @GetMapping("/currentUserId")
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    @GetMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }

    @GetMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    @PutMapping("/updateUserEmail")
    public RespVO updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            return new RespVO("success", "开启成功");
        }
        return new RespVO("error", "开启失败");
    }


}
