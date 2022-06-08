package com.example.redem.service;

import com.example.redem.entity.purchaseResult;
import com.example.redem.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class purchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    public Map<String, Object> searchPurchase(@RequestParam String clientId,
                                        @RequestParam String fundCode) {
        fundCode = "%" + fundCode + "%";
        clientId = "%" + clientId + "%";
        List<purchaseResult> data = purchaseMapper.findAll(clientId, fundCode);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        return res;
    }
    public boolean submitPurchase(@RequestParam String clientId,
                                              @RequestParam String fundCode,
                                              @RequestParam String bankAccount,
                                              @RequestParam Date purchaseDate,
                                              @RequestParam Double purchaseCount) {

        return purchaseMapper.savepurchase(clientId, fundCode,bankAccount,purchaseDate,purchaseCount);

    }


    public boolean serialnumPurchase(@RequestParam String clientId,
                                  @RequestParam String fundCode,
                                  @RequestParam String bankAccount,
                                  @RequestParam Date purchaseDate,
                                  @RequestParam Double purchaseCount) {



        return purchaseMapper.updatepurchase(clientId, fundCode,bankAccount,purchaseDate,purchaseCount);

    }

}
