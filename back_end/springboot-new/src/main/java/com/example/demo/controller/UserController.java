package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.adminLogin;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    // 新增和修改
    @RequestMapping(value = "save", produces = {"application/json;charset=UTF-8"})
    public boolean save(@RequestBody JSONObject json) {
        String clientId = json.getString("clientId");
        String clientName = json.getString("clientName");
        String clientGender = json.getString("clientGender");
        Integer clientAge = json.getInt("clientAge");
        String clientOrganFlag = json.getString("clientOrganFlag");
        String clientIdKind = json.getString("clientIdKind");
        String clientPhnumber = json.getString("clientPhnumber");
        String clientRisk = json.getString("clientRisk");
        String bankAccount = json.getString("bankAccount");
        String bcb = json.getString("bankCurrentBalance");
        BigDecimal bankCurrentBalance = new BigDecimal(bcb);
        return userService.saveUser(clientId,clientName,clientGender,clientAge,clientOrganFlag,clientIdKind,clientPhnumber,clientRisk,bankAccount,bankCurrentBalance);
    }

    @RequestMapping(value = "update", produces = {"application/json;charset=UTF-8"})
    public boolean update(@RequestBody JSONObject json) {
        String clientId = json.getString("clientId");
        String clientName = json.getString("clientName");
        String clientGender = json.getString("clientGender");
        Integer clientAge = json.getInt("clientAge");
        String clientOrganFlag = json.getString("clientOrganFlag");
        String clientIdKind = json.getString("clientIdKind");
        String clientPhnumber = json.getString("clientPhnumber");
        String clientRisk = json.getString("clientRisk");
        return userService.updateUser(clientId,clientName,clientGender,clientAge,clientOrganFlag,clientIdKind,clientPhnumber,clientRisk);
    }

    // 查询所有数据
    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @DeleteMapping("/{clientId}")
    public boolean delete(@PathVariable String clientId) {
        return userService.removeById(clientId);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<String> ids) { // [1,2,3]
        return userService.removeByIds(ids);
    }

    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
//    limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username) {
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String clientId,
                                @RequestParam(defaultValue = "") String clientName,
                                @RequestParam(defaultValue = "") String clientRisk,
                                @RequestParam(defaultValue = "") String clientOrganFlag) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(clientId)) {
            queryWrapper.like("client_id",clientId);
        }
        if (!"".equals(clientName)) {
            queryWrapper.like("client_name", clientName);
        }
        if (!"".equals(clientRisk)) {
            queryWrapper.like("client_risk", clientRisk);
        }
        if (!"".equals(clientOrganFlag)) {
            queryWrapper.like("client_organ_flag", clientOrganFlag);
        }
        System.out.println(queryWrapper.toString());
        return userService.page(page, queryWrapper);

    }

}