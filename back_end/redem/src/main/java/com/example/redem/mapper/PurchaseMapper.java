package com.example.redem.mapper;

import com.example.redem.entity.purchaseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Mapper
public interface PurchaseMapper {
    List<purchaseResult> findAll( @Param("clientId") String clientId, @Param("fundCode") String fundCode);

    boolean savepurchase(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("purchaseDate") Date purchaseDate, @Param("purchaseCount") Double purchaseCount);

    boolean updatepurchase(@Param("clientId") String clientId, @Param("fundCode") String fundCode, @Param("bankAccount") String bankAccount, @Param("purchaseDate") Date purchaseDate, @Param("purchaseCount") Double purchaseCount);
}

