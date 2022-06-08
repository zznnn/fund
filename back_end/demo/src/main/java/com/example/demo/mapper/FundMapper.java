package com.example.demo.mapper;

import com.example.demo.entity.Fund;
import com.example.demo.vo.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-24
 * Time: 13:12
 */
public interface FundMapper {
    @Select("select * from fund limit #{pageNum},#{pageSize} ")
    @Results({
            @Result(column="fund_code", property="fundCode", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_name", property="fundName", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_netvalue", property="fundNetValue", jdbcType= JdbcType.DECIMAL),
            @Result(column="fund_risk", property="fundRisk", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_birth", property="fundBirth", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_trustee", property="fundTrustee", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_manager", property="fundManager", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_currency", property="fundCurrency", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_others", property="fundOthers", jdbcType= JdbcType.VARCHAR),

    })
    List<Fund> findByPage(Integer pageNum, Integer pageSize);
    @Select("select count(fund_code) from fund ")
    Integer countFund();

    @Update("UPDATE `fund` SET `fund_netvalue` = `fund_netvalue`*(rand()/5+0.9) ")
    @Transactional
    public void update();

    @Insert("INSERT INTO `trend_record` ( `fund_code`, `fund_netvalue`, `date_time` ) SELECT fund_code,fund_netvalue,date_time FROM fund, date;")
    void record();
}
