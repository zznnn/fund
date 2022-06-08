package com.example.demo.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 0:18
 */
@Data
public class Trend {
    private Integer trendRecordId;
    private String fundCode;
    private Double fundNetValue;
    private Date dateTime;
}
