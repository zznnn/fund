package com.example.demo.mapper;

import com.example.demo.entity.BankJour;
import com.example.demo.entity.Datetime;
import com.example.demo.entity.Redem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-25
 * Time: 20:47
 */
public interface RedemMapper {



    //更新赎回申请表内对应id的赎回申请的剩余份额
    @Update("UPDATE redem r, purchase p SET r.redem_current_share = p.purchase_share WHERE r.client_id = p.client_id and r.fund_code = p.fund_code ;")
    void setResidue();

    //计算出对应的收益
    @Update("UPDATE redem r,fund f SET r.redem_count = f.fund_netvalue * r.redem_share,r.redem_current_share = r.redem_current_share - r.redem_share WHERE f.fund_code = r.fund_code;")
    void setCountAndResidue();

    @Update("UPDATE redem SET redem_status = '已确认' WHERE redem_date=#{date}")
    void confirm(Date date);

    @Select("select * from redem where redem_date=#{date} limit #{pageNum},#{pageSize} ")
    @Results({
            @Result(column="redem_id", property="redemId", jdbcType= JdbcType.VARCHAR),
            @Result(column="client_id", property="clientId", jdbcType= JdbcType.VARCHAR),
            @Result(column="bank_account", property="bankAccount", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_code", property="fundCode", jdbcType= JdbcType.VARCHAR),
            @Result(column="redem_date", property="redemDate", jdbcType= JdbcType.DATE),
            @Result(column="redem_share", property="redemShare", jdbcType= JdbcType.DOUBLE),
            @Result(column="redem_count", property="redemCount", jdbcType= JdbcType.DOUBLE),
            @Result(column="redem_current_share", property="redemCurrentShare", jdbcType= JdbcType.DOUBLE),
            @Result(column="redem_estimated_date", property="redemEstimatedDate", jdbcType= JdbcType.DATE),
            @Result(column="redem_status", property="redemStatus", jdbcType= JdbcType.VARCHAR),
    })
    List<Redem> findByPage(Integer pageNum, Integer pageSize,Date date);

    @Select("select count(redem_id) from redem ")
    Integer countRedem();

    @Select("SELECT redem_estimated_date as bankJourId, bank_account AS bankAccount, redem_count as bankChange FROM redem where redem_estimated_date=#{date};")
    List<BankJour> getRedemJour(Date date);
}
