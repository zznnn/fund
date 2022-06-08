package com.example.demo.mapper;

import com.example.demo.entity.Trend;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-28
 * Time: 14:32
 */
public interface TrendMapper {

    @Select("SELECT fund_code, fund_netvalue , date_time  FROM trend_record where fund_code=#{code}")
    @Results({
            @Result(column="fund_code", property="fundCode", jdbcType= JdbcType.VARCHAR),
            @Result(column="fund_netvalue", property="fundNetValue", jdbcType= JdbcType.DOUBLE),
            @Result(column="date_time", property="dateTime", jdbcType= JdbcType.DATE),
    })
    List<Trend> findByCode(String code);
}
