package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
@TableName(value = "client")
public class User {
    @TableId(type = IdType.AUTO)
    private String clientId;
    private String clientName;
    private String clientGender;
    private Integer clientAge;
    private String clientOrganFlag;
    private String  clientIdKind;
    private String clientPhnumber;
    private String clientRisk;

}