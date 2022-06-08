package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.entity.adminLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface adminMapper extends BaseMapper<adminLogin> {
}
