package com.example.demo.mapper;

import com.example.demo.entity.Bank;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 14:39
 */
public interface BankMapper {

    @Select("select * from bank ")
    @Results({
            @Result(column="bank_account", property="bankAccount", jdbcType= JdbcType.VARCHAR),
            @Result(column="client_id", property="clientId", jdbcType=JdbcType.VARCHAR),
            @Result(column = "bank_current_balance", property ="bankCurrentBalance",jdbcType =JdbcType.DECIMAL)
    })
    List<Bank>findAll();

    @Insert("INSERT INTO `bank`(`bank_account`, `client_id`, `bank_current_balance`) VALUES (#{bankAccount}, #{clientId}, #{bankCurrentBalance}) ")
    @Transactional
    void saveBank(Bank bank);

    @Update("UPDATE bank b,redem r SET b.bank_current_balance =b.bank_current_balance+r.redem_count  WHERE r.redem_estimated_date=#{date} AND r.client_id=b.client_id;")
    void updateCurrentBalance(Date date);
}
