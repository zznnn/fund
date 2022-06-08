package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.adminLogin;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {


    @Autowired
    private UserMapper userMapper;

    public boolean saveUser(@RequestParam String clientId,
                            @RequestParam String clientName,
                            @RequestParam String clientGender,
                            @RequestParam Integer clientAge,
                            @RequestParam String clientOrganFlag,
                            @RequestParam String clientIdKind,
                            @RequestParam String clientPhnumber,
                            @RequestParam String clientRisk,
                            @RequestParam String bankAccount,
                            @RequestParam BigDecimal bankCurrentBalance){
        return userMapper.save(clientId,clientName,clientGender,clientAge,clientOrganFlag,clientIdKind,clientPhnumber,clientRisk,bankAccount,bankCurrentBalance);

    }


    public boolean updateUser(@RequestParam String clientId,
                            @RequestParam String clientName,
                            @RequestParam String clientGender,
                            @RequestParam Integer clientAge,
                            @RequestParam String clientOrganFlag,
                            @RequestParam String clientIdKind,
                            @RequestParam String clientPhnumber,
                            @RequestParam String clientRisk){
        return userMapper.update(clientId,clientName,clientGender,clientAge,clientOrganFlag,clientIdKind,clientPhnumber,clientRisk);

    }


    }
//        if (user.getId() == null) {  // user没有id，则表示是新增
//            return userMapper.insert(user);
//        } else { // 否则为更新
//            return userMapper.update(user);
//        }
//    }


