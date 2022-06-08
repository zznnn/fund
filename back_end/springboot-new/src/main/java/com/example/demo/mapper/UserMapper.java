package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    boolean save(@Param("clientId") String clientId, @Param("clientName") String clientName, @Param("clientGender") String clientGender, @Param("clientAge") Integer clientAge, @Param("clientOrganFlag") String clientOrganFlag,@Param("clientIdKind") String clientIdKind,@Param("clientPhnumber") String clientPhnumber,@Param("clientRisk") String clientRisk,@Param("bankAccount") String bankAccount,@Param("bankCurrentBalance") BigDecimal bankCurrentBalance);

    boolean update(@Param("clientId") String clientId, @Param("clientName") String clientName, @Param("clientGender") String clientGender, @Param("clientAge") Integer clientAge, @Param("clientOrganFlag") String clientOrganFlag,@Param("clientIdKind") String clientIdKind,@Param("clientPhnumber") String clientPhnumber,@Param("clientRisk") String clientRisk);
}