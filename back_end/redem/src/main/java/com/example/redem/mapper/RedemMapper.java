package com.example.redem.mapper;

import com.example.redem.entity.Redem;
import com.example.redem.entity.purchaseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface RedemMapper {

    boolean saveredem(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("redemShare") Integer redemShare);

    List<Redem> getDate();

    boolean updateredem(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("redemShare") Integer redemShare, @Param("purchaseShare") Integer purchaseShare);

}
