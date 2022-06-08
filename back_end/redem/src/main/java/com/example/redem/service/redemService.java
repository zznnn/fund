package com.example.redem.service;

import com.example.redem.entity.Redem;
import com.example.redem.entity.purchaseResult;
import com.example.redem.mapper.PurchaseMapper;
import com.example.redem.mapper.RedemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class redemService {
    @Autowired
    private RedemMapper redemMapper;

    public boolean submitRedem(@RequestParam String clientId,
                                  @RequestParam String fundCode,
                                  @RequestParam String bankAccount,
                                  @RequestParam Integer redemShare) {

        return redemMapper.saveredem(clientId, fundCode,bankAccount,redemShare);

    }

    public Map<String, Object> getDate() {

        List<Redem> date = redemMapper.getDate();
        Map<String, Object> res = new HashMap<>();
        res.put("data", date);
        return res;

    }

    public boolean serialnumRedem(@RequestParam String clientId,
                                                  @RequestParam String fundCode,
                                                  @RequestParam String bankAccount,
                                                  @RequestParam Integer redemShare,
                                                  @RequestParam Integer purchaseShare) {


        return redemMapper.updateredem(clientId, fundCode,bankAccount,redemShare,purchaseShare);

    }


}
