package com.blcoder.competitionmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.blcoder.competitionmanager.common.R;
import com.blcoder.competitionmanager.entity.Users;
import com.blcoder.competitionmanager.service.UsersService;
import com.blcoder.competitionmanager.utils.VaildateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public R<String> check(@RequestBody Map map, HttpSession session){
        log.info(map.toString());
        String email = map.get("email").toString();
        String code = map.get("code").toString();
        Object codeInSession = session.getAttribute(email);
        if (codeInSession != null && codeInSession.equals(code)) {
            return R.success("验证码正确");
        }
        return R.error("验证码错误");
    }

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody Users user, HttpSession session){
        log.info("用户信息：" + user);
        //检查是否存在该用户
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,user.getUsername());
        Users one = usersService.getOne(queryWrapper);

        //不存在返回错误
        if (one == null){
            return R.error("不存在该用户");
        }

        //检查邮箱是否正确，不正确返回错误
        if (!one.getEmail().equals(user.getEmail())){
            return R.error("邮箱不正确");
        }

        //发送验证码
        String email = user.getEmail();
        if (StringUtils.isNotEmpty(email)) {
            // 生成验证码
            String code = VaildateCodeUtils.generateVerificationCode();

            // 发送验证码到用户邮箱
            boolean result = VaildateCodeUtils.sendVerificationCode(email, code);

            if (result) {
                // 将验证码存储在 session 中，供后续验证使用
                session.setAttribute(email, code);
                return R.success("验证码已发送到您的邮箱");
            } else {
                return R.error("验证码发送失败");
            }
        }
        return R.error("邮箱不能为空");
    }

    @PutMapping("/reset")
    public R<Users> reset(HttpServletRequest request, @RequestBody Users users){
        //1.将页面提交的密码进行md5加密处理
        String password = users.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaUpdateWrapper<Users> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Users::getUsername,users.getUsername());
        wrapper.set(Users::getPassword,password);
        usersService.update(wrapper);

        //3.修改成功，将用户id存入Session并返回登录成功结果
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Users::getUsername,users.getUsername());
        Users one = usersService.getOne(queryWrapper);
        request.getSession().setAttribute("user",one.getId());
        return R.success(users);
    }
}
