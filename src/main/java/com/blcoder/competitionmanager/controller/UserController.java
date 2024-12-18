package com.blcoder.competitionmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blcoder.competitionmanager.common.R;
import com.blcoder.competitionmanager.entity.Users;
import com.blcoder.competitionmanager.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UsersService usersService;


    @PostMapping("/login")
    public R<Users> login(HttpServletRequest request, @RequestBody Users users){

        //1.将页面提交的密码进行md5加密处理
        String password = users.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,users.getUsername());
        Users emp = usersService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if(emp==null){
            return R.error("登录失败");
        }

        //4.密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        //5.登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",emp.getId());
        return R.success(emp);
    }
}
