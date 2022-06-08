package com.example.demo.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-28
 * Time: 14:30
 */
@Data
public class Trend {
    private String fundCode;
    private Double fundNetValue;
    private Date dateTime;
}

