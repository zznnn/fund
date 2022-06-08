package com.example.demo.mapper;

import com.example.demo.entity.Datetime;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-23
 * Time: 13:37
 */
public interface DatetimeMapper {

    @Select("select * from date ")
    @Results({
            @Result(column = "date_id", property = "dateId", jdbcType = JdbcType.INTEGER),
            @Result(column = "date_time", property = "dateTime", jdbcType = JdbcType.DATE)
    })
    Datetime getSystime();

    @Update("UPDATE `date` SET `date_time` = #{dateTime} WHERE `date_id` = #{dateId};")
    @Transactional
    void setSystime(Datetime datetime);

    @Select("SELECT date_id, DATE_ADD(date_time,INTERVAL -1 DAY) date_time from date where `date_id` = 1;")
    @Results({
            @Result(column = "date_id", property = "dateId", jdbcType = JdbcType.INTEGER),
            @Result(column = "date_time", property = "dateTime", jdbcType = JdbcType.DATE)
    })
    Datetime getPreDay();

    @Update("UPDATE `date` SET `date_time` = DATE_ADD(date_time,INTERVAL 1 DAY) WHERE `date_id` = 1")
    @Transactional
    void addSystime();

    @Select("SELECT date_id, DATE_ADD(date_time,INTERVAL -2 DAY) date_time from date where `date_id` = 1;")
    @Results({
            @Result(column = "date_id", property = "dateId", jdbcType = JdbcType.INTEGER),
            @Result(column = "date_time", property = "dateTime", jdbcType = JdbcType.DATE)
    })
    Datetime getPre2Day();
}
