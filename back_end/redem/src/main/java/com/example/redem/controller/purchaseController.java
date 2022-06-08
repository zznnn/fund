package com.example.redem.controller;

import com.example.redem.service.purchaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class purchaseController {
    @Autowired
    private purchaseService purchaseService;

    @RequestMapping(value = "purchase", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> searchPurchase(@RequestBody JSONObject json) {
        System.out.println("json数据为 : " + json.toString());
        String clientId = json.getString("clientId");
        String fundCode = json.getString("fundCode");
        return purchaseService.searchPurchase(clientId,fundCode);
    }

    @RequestMapping(value = "purchase/submit", produces = {"application/json;charset=UTF-8"})
    public boolean submitPurchase(@RequestBody JSONObject json) throws ParseException {
        System.out.println("json数据为 : " + json.toString());
        String clientId = json.getString("clientId");
        String fundCode = json.getString("fundCode");
        String bankAccount = json.getString("bankAccount");
        String dateStr = json.getString("purchaseDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date purchaseDate = sdf.parse(dateStr);
        Double purchaseCount = json.getDouble("purchaseCount");
        return purchaseService.submitPurchase(clientId, fundCode,bankAccount,purchaseDate,purchaseCount);
    }

    @RequestMapping(value = "purchase/serialnum", produces = {"application/json;charset=UTF-8"})
    public boolean serialnumPurchase(@RequestBody JSONObject json) throws ParseException {
        System.out.println("json数据为 : " + json.toString());
        String clientId = json.getString("clientId");
        String fundCode = json.getString("fundCode");
        String bankAccount = json.getString("bankAccount");
        String dateStr = json.getString("purchaseDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date purchaseDate = sdf.parse(dateStr);
        Double purchaseCount = json.getDouble("purchaseCount");
        return purchaseService.serialnumPurchase(clientId, fundCode,bankAccount,purchaseDate,purchaseCount);
    }
}

