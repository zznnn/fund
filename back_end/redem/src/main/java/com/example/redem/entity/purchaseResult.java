package com.example.redem.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
@Data
public class purchaseResult {
//    private String purchaseId;
    private String clientId;
    private String bankAccount;
    private double bankCurrentBalance;
    private String fundCode;
    private Date purchaseDate;
//    private BigDecimal purchaseCount;
//    private BigDecimal purchaseShare;
//    private String purchaseStatus;
    private String clientName;
    private String fundName;
    private String clientRisk;
    private String fundRisk;

//    private Date dateTime;

}
