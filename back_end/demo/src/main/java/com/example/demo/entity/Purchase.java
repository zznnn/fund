package com.example.demo.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 0:04
 */
@Data
public class Purchase {
    private String purchaseId;
    private String clientId;
    private String bankAccount;
    private String fundCode;
    private Date purchaseDate;
    private Double purchaseCount;
    private Double purchaseShare;
    private String purchaseStatus;
}
