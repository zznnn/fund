package com.example.demo.controller;

import com.example.demo.entity.Fund;
import com.example.demo.mapper.FundMapper;
import com.example.demo.vo.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 13:11
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Resource
    FundMapper fundMapper;

    @GetMapping("/page")
    public Page<Fund> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        List<Fund> fundDate = fundMapper.findByPage(offset, pageSize);
        Page<Fund> page = new Page<>();

        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(fundDate);
        Integer total = fundMapper.countFund();
        page.setTotal(total);
        return page;
    }


    @PutMapping("/update")
    public Boolean updateByPage(){
        fundMapper.update();
        fundMapper.record();
        System.out.println("基金净值更新成功");
        return true;
    }
}
