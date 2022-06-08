package com.example.businessearch.controller;

import com.example.businessearch.service.redemService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;
@RestController
public class redemController {
    @Autowired
    private com.example.businessearch.service.redemService redemService;

    @RequestMapping(value = "getDate", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> getDate(@RequestBody JSONObject json) {
        System.out.println("json数据为 : " + json.toString());
        return redemService.getDate();
    }

    @RequestMapping(value = "redem/submit", produces = {"application/json;charset=UTF-8"})
    public boolean submitRedem(@RequestBody JSONObject json) throws ParseException {
        System.out.println("json数据为 : " + json.toString());
        String clientId = json.getString("clientId");
        String fundCode = json.getString("fundCode");
        String bankAccount = json.getString("bankAccount");
//        String dateStr = json.getString("redemDate");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date redemDate = sdf.parse(dateStr);
        String redemSh=json.getString("redemShare");
        BigDecimal redemShare = new BigDecimal(redemSh);
        return redemService.submitRedem(clientId, fundCode,bankAccount,redemShare);
    }

    @RequestMapping(value = "redem/serialnum", produces = {"application/json;charset=UTF-8"})
    public boolean serialnumRedem(@RequestBody JSONObject json) throws ParseException {
        System.out.println("json数据为 : " + json.toString());
        String clientId = json.getString("clientId");
        String fundCode = json.getString("fundCode");
        String bankAccount = json.getString("bankAccount");
//        String dateStr = json.getString("redemDate");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date redemDate = sdf.parse(dateStr);
        String redemSh=json.getString("redemShare");
        BigDecimal redemShare = new BigDecimal(redemSh);
        String purchaseSh=json.getString("purchaseShare");
        BigDecimal purchaseShare = new BigDecimal(purchaseSh);
        return redemService.serialnumRedem(clientId,fundCode,bankAccount,redemShare,purchaseShare);
    }
}
