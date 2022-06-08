package com.example.demo.entity;

import lombok.Data;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 14:38
 */
@Data
public class Bank {
    private String bankAccount;
    private String clientId;
    private Double bankCurrentBalance;
}
