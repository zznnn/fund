package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "fund")
public class Product {
    @TableId(type = IdType.AUTO)
    private String fundCode;
    private String fundName;
    private BigDecimal fundNetvalue;
    private String fundRisk;
    private Date fundBirth;
    private String fundTrustee;
    private String fundManager;
    private String fundCurrency;
    private String fundOthers;
}
