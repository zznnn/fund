package com.example.demo.entity;


import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 0:10
 */
@Data
public class Redem {
    private String redemId;
    private String clientId;
    private String bankAccount;
    private String fundCode;
    private Date redemDate;
    private Double redemShare;
    private Double redemCount;
    private Double redemCurrentShare;
    private Date redemEstimatedDate;
    private String redemStatus;
}
