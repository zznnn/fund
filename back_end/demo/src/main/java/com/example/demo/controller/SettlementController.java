package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.vo.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zzn
 * Date: 2022-05-25
 * Time: 18:06
 */
@RestController
@RequestMapping("/settlement")
public class SettlementController {

    @Resource
    BankMapper bankMapper;

    @Resource
    BankJourMapper bankJourMapper;

    @Resource
    DatetimeMapper datetimeMapper;

    @Resource
    PurchaseMapper purchaseMapper;

    @Resource
    RedemMapper redemMapper;


    /*        @PutMapping("/ss")
            public String ss(){
                receivePurchase();
                receiveRedem();
                return "";
            }*/
    //计算T日申购对应的份额
    @PutMapping("/receivePurchase")
    public String receivePurchase() {
        System.out.println("计算申购中...");
        Date date = datetimeMapper.getPreDay().getDateTime();
        System.out.println("T日为: " + date.toString());
        purchaseMapper.setShare(date);
        System.out.println("申购对应份额计算成功！");
        return "T日申购对应的份额计算成功";
    }

    @PutMapping("/receiveRedem")
    public String receiveRedem() {
        System.out.println("计算赎回中...");
        Date date = datetimeMapper.getPreDay().getDateTime();
        System.out.println("T日为: " + date.toString());
        //根据申购导入赎回的份额
        redemMapper.setResidue();
        redemMapper.setCountAndResidue();
        System.out.println("赎回对应的收益和剩余份额计算成功！");
        return "T日赎回对应的收益和剩余份额计算成功";
    }

    //T-1赎回打钱，根据预计到账日期为T的赎回申请打钱
    @PutMapping("/bankGetCount")
    public String setBank() {
        System.out.println("T-1日赎回收益录入中...");
        Date date = datetimeMapper.getSystime().getDateTime();
        //修改银行卡信息
        bankMapper.updateCurrentBalance(date);

        //得到流水信息
        //List<BankJour> bankJourList = redemMapper.getRedemJour(date);
        //foreach插入
        //bankJourMapper.insert(bankJourList);

        bankJourMapper.insert2(date);//出问题的话试试这行

        //重写流水编号
        bankJourMapper.update();

        return "bankGetCount success";
    }

    @PutMapping("/confirm")
    public String confirm() {
        System.out.println("申请确认中...");
        Date date = datetimeMapper.getPreDay().getDateTime();
        System.out.println("T日为: " + date.toString());
        purchaseMapper.confirm(date);
        redemMapper.confirm(date);
        System.out.println("申请状态修改成功");
        return "确认流程成功";
    }


    @GetMapping("/purchasePage")
    public Page<Purchase> purchaseFindByDate(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;

        Date date=datetimeMapper.getPreDay().getDateTime();
        List<Purchase> purchaseDate = purchaseMapper.findByPage(offset, pageSize,date);
        Page<Purchase> page = new Page<>();

        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(purchaseDate);

        Integer total = purchaseMapper.countPurchase();
        page.setTotal(total);
        return page;
    }

    @GetMapping("/redemPage")
    public Page<Redem> redemFindByDate(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;

        Date date=datetimeMapper.getPreDay().getDateTime();
        List<Redem> redemDate = redemMapper.findByPage(offset, pageSize,date);
        Page<Redem> page = new Page<>();

        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(redemDate);
        Integer total = redemMapper.countRedem();
        page.setTotal(total);
        return page;
    }

    @GetMapping("/bankPage")
    public Page<BankJour> findByDate(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;


        String str=new SimpleDateFormat("yyyyMMdd")
                .format(datetimeMapper.getPreDay().getDateTime());
        List<BankJour> bankJourDate = bankJourMapper.findByPage(offset, pageSize, str);
        Page<BankJour> page = new Page<>();

        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(bankJourDate);
        Integer total = bankJourMapper.countFund();
        page.setTotal(total);
        return page;
    }

/*    @GetMapping("/getList")
    public List<BankJour> getList(){

        return redemMapper.getRedemJour();
    }*/

}
