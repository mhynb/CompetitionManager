package com.blcoder.competitionmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blcoder.competitionmanager.common.R;
import com.blcoder.competitionmanager.entity.Users;
import com.blcoder.competitionmanager.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/register")
    public R<String> save(HttpServletRequest request,@RequestBody Users users){
        log.info("新增用户信息:{}",users.toString());
        String password = users.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        users.setPassword(password);
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,users.getUsername());
        Users one = usersService.getOne(queryWrapper);
        if(one != null){
            return R.error("用户名已存在，请重新输入");
        }
        usersService.save(users);
        one = usersService.getOne(queryWrapper);
        request.getSession().setAttribute("user",one.getId());
        return R.success("新增员工成功");
    }

    @PostMapping("/check")
    public R<String> check(@RequestBody Users users){
        log.info("提交用户信息：" + users);
        //检查是否存在该用户
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,users.getUsername());
        Users one = usersService.getOne(queryWrapper);

        //不存在返回错误
        if (one == null){
            return R.error("不存在该用户");
        }

        //检查邮箱是否正确，不正确返回错误
        if (one.getEmail() != users.getEmail()){
            return R.error("邮箱不正确");
        }

        return R.success("填写正确，可以发送验证码");
    }

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody Users user, HttpSession session){
        String phone = user.getEmail();
        if(StringUtils.isNotEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            SMSUtils.sendMessage("饭点","SMS_474935881",phone,code);
            session.setAttribute(phone,code);
            return R.success("手机验证码发送成功");
        }
        return R.error("短信发送失败");
    }
}
