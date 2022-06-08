package com.example.redem.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Purchase {
    private String purchaseId;
    private String clentId;
    private String bankAccount;
    private String fundCode;
    private Date purchaseDate;
    private Double purchaseCount; //交易金额
    private Integer purchaseShare;//对应分额
    private String purchaseStatus;
}
