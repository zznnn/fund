package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private ProductService productService;

    // 新增和修改
    @PostMapping
    public boolean save(@RequestBody Product product) {
        // 新增或者更新
        return productService.saveProduct(product);
    }
    @DeleteMapping("/{fundCode}")
    public boolean delete(@PathVariable String fundCode) {
        return productService.removeById(fundCode);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<String> ids) { // [1,2,3]
        return productService.removeByIds(ids);
    }

    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public IPage<Product> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String fundName,
                                @RequestParam(defaultValue = "") String fundCode,
                                @RequestParam(defaultValue = "") String fundManager,
                                @RequestParam(defaultValue = "") String fundRisk) {
        IPage<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!"".equals(fundName)) {
            queryWrapper.like("fund_name", fundName);
        }
        if (!"".equals(fundCode)) {
            queryWrapper.like("fund_code",fundCode);
        }
        if (!"".equals(fundManager)) {
            queryWrapper.like("fund_manager", fundManager);
        }
        if (!"".equals(fundRisk)) {
            queryWrapper.like("fund_risk", fundRisk);
        }
        return productService.page(page, queryWrapper);
    }
}
