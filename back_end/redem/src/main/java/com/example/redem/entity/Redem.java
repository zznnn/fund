package com.example.redem.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Redem {

    private String redemId;
    private String clentId;
    private String bankAccount;
    private String fundCode;
    private Date redemDate;
    private Integer redemShare;
    private Integer purchaseShare;
    private BigDecimal redemCount;
    private Integer redemCurrentShare;
    private Date redemEstimatedDate;
    private String redemStatus;
    private double bankCurrentBalance;

}
