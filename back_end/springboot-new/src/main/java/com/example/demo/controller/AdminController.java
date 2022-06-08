package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.example.demo.entity.adminLogin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public boolean login(@RequestBody adminLogin admin) {
        String username =admin.getUsername();
        String password = admin.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return false;
        }
        return adminService.login(admin);
    }
}
