package com.example.demo.mapper;

import com.example.demo.entity.Datetime;
import com.example.demo.entity.Purchase;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-25
 * Time: 18:07
 */
public interface PurchaseMapper {

    @Update("UPDATE purchase p,fund f SET p.purchase_share = p.purchase_count/f.fund_netvalue WHERE p.purchase_date = #{date} AND f.fund_code = p.fund_code")
    void setShare(Date date);

    @Update("UPDATE purchase SET purchase_status = '已确认' WHERE purchase_date=#{date}")
    void confirm(Date date);

    @Select("select * from purchase where purchase_date=#{date} limit #{pageNum},#{pageSize} ")
    @Results({
            @Result(column="purchase_id", property="purchaseId", jdbcType= JdbcType.VARCHAR),
            @Result(column="client_id", property="clientId", jdbcType= JdbcType.VARCHAR),
            @Result(column="bank_account", property="bankAccount", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_code", property="fundCode", jdbcType= JdbcType.VARCHAR),
            @Result(column="purchase_date", property="purchaseDate", jdbcType= JdbcType.DATE),
            @Result(column="purchase_count", property="purchaseCount", jdbcType= JdbcType.DOUBLE),
            @Result(column="purchase_share", property="purchaseShare", jdbcType= JdbcType.DOUBLE),
            @Result(column="purchase_status", property="purchaseStatus", jdbcType= JdbcType.VARCHAR),
    })
    List<Purchase> findByPage(Integer pageNum, Integer pageSize, Date date);

    @Select("select count(purchase_id) from purchase ")
    Integer countPurchase();
}
