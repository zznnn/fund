package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
@TableName(value = "admin")
public class adminLogin {
    @TableId(type = IdType.AUTO)
    private String username;
    private String password;
}
