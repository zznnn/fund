package com.example.businessearch.service;

import com.example.businessearch.Mapper.RedemMapper;
import com.example.businessearch.pojo.Redem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
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
                               @RequestParam BigDecimal redemShare) {

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
                                  @RequestParam BigDecimal redemShare,
                                  @RequestParam BigDecimal purchaseShare) {


        return redemMapper.updateredem(clientId, fundCode,bankAccount,redemShare,purchaseShare);

    }

}
