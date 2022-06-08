package com.example.demo.controller;

import com.example.demo.entity.Datetime;
import com.example.demo.mapper.DatetimeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 13:41
 */
@RestController
@RequestMapping("/datetime")
public class DatetimeController {


    @Resource
    DatetimeMapper datetimeMapper;

    @GetMapping
    public Datetime getSystime(){

        //得到字符串格式的时间
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(datetimeMapper.getSystime().getDateTime());
        //控制台输出系统时间
        System.out.println("get: System Time is : "+dateStr);
        //返回一个json格式数据
        return datetimeMapper.getSystime();
    }

    @PutMapping
    public String setSystime(@RequestBody Datetime datetime){
        //更新系统时间
        datetimeMapper.setSystime(datetime);
        //get datetime String
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(datetimeMapper.getSystime().getDateTime());
        //控制台输出系统时间
        System.out.println("put: System Time is : "+dateStr);
        return "update System time success";
    }

    @PutMapping("addDay")
    public String addSystime(){
        datetimeMapper.addSystime();
        return "add Day success";
    }
}
