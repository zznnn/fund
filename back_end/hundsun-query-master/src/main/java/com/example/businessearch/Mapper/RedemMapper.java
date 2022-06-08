package com.example.businessearch.Mapper;

import com.example.businessearch.pojo.Redem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
@Mapper
public interface RedemMapper {

    boolean saveredem(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("redemShare") BigDecimal redemShare);

    List<Redem> getDate();

    boolean updateredem(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("redemShare") BigDecimal redemShare, @Param("purchaseShare") BigDecimal purchaseShare);
}
