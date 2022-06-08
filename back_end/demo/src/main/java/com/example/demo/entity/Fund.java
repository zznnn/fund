package com.example.demo.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 23:51
 */
@Data
public class Fund {
    private String fundCode;
    private String fundName;
    private Double fundNetValue;
    private String fundRisk;
    private String fundBirth;
    private String fundTrustee;
    private String fundManager;
    private String fundCurrency;
    private String fundOthers;

}
