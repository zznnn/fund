package com.example.demo.controller;

import com.example.demo.entity.Trend;
import com.example.demo.mapper.TrendMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-28
 * Time: 14:28
 */
@RestController
@RequestMapping("/trend")
public class TrendController {

    @Resource
    TrendMapper trendMapper;

    @GetMapping("/getTrend")
    public List<Trend> getTrend(@RequestParam String fundCode){
        System.out.println(trendMapper.findByCode(fundCode));
        return trendMapper.findByCode(fundCode);
    }
}
