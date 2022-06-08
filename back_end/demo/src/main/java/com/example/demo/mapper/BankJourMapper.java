package com.example.demo.mapper;

import com.example.demo.entity.BankJour;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-26
 * Time: 15:22
 */
public interface BankJourMapper {
    @Insert({"<script> insert into bank_jour(bank_jour_id,bank_account, bank_change)" +
            " values" +
            " <foreach collection=\"list\" item=\"item\" separator=\",\" >" +
            "        (#{item.bankJourId},#{item.bankAccount},#{item.bankChange})" +
            " </foreach>" +
            "</script>"})
    void insert(List<BankJour> list);

    @Insert("INSERT INTO `bank_jour` ( bank_jour_id,bank_account, bank_change ) SELECT DATE_FORMAT(redem_estimated_date,'%Y%m%d') as bankJourId, bank_account AS bankAccount, redem_count as bankChange FROM redem where redem_estimated_date=#{date};")
    void insert2(Date date);

    @Update("UPDATE bank_jour SET `bank_jour_id` = CONCAT(left(bank_jour_id,8),'B',right(concat('000',id),4))")
    void update();

    @Select("select * from bank_jour where bank_jour_id like'${str}%' limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column="bank_jour_id", property="bankJourId", jdbcType= JdbcType.VARCHAR),
            @Result(column="bank_account", property="bankAccount", jdbcType= JdbcType.VARCHAR),
            @Result(column="bank_change", property="bankChange", jdbcType= JdbcType.DOUBLE),
    })
    List<BankJour> findByPage(Integer pageNum, Integer pageSize ,String str);

    @Select("select count(id) from bank_jour ")
    Integer countFund();
}
