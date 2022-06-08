package com.example.demo.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 13:46
 */
@Data
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
}
