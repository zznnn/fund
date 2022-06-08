package com.example.demo.controller;

import com.example.demo.entity.Bank;
import com.example.demo.mapper.BankMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 14:41
 */
@RestController
@RequestMapping("/bank")
public class BankController {
    @Resource
    BankMapper bankMapper;

    @GetMapping
    public List<Bank> getBank(){
        System.out.println(bankMapper.findAll());
        return bankMapper.findAll();
    }

    @PostMapping
    public String addBank(@RequestBody Bank bank){
        bankMapper.saveBank(bank);
        System.out.println(bank.toString());
        return "success";
    }
}
