package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.adminLogin;
import com.example.demo.mapper.adminMapper;
import org.springframework.stereotype.Service;
@Service
public class AdminService extends ServiceImpl<adminMapper,adminLogin> {

    public boolean login(adminLogin admin) {

        QueryWrapper<adminLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        queryWrapper.eq("password", admin.getPassword());
        // 处理异常情况
        try {
            adminLogin one = getOne(queryWrapper);
            return one != null;
        } catch (Exception e) {
            return false;
        }
    }
}
