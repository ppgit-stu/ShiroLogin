package com.shirologin.controller;

import com.shirologin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author pengyong
 * @date 2020/5/10 - 13:07
 */
@Controller
public class LoginController {

    //service注入Controller
    private UserService userService;

    @Resource
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String showResult() {
        return "login";
    }

    @RequestMapping(value = "/logingo",method = RequestMethod.POST)
    public String login(@RequestParam("name") String name, @RequestParam("password") String password,
                        Model model) {
        /*UserBean userBean = userService.login(name, password);
        if (userBean != null) {
            return "success";
        } else {
            return "error";
        }*/

        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3.执行登录方法
        try {
            //登陆成功
            subject.login(token);
            model.addAttribute("username", token.getUsername());

            //跳转
            return "index";
        } catch (UnknownAccountException e) {
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在，请重新登陆!!！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误，请重新登录!!！");
            return "login";
        }
    }

    /*登出方式1*/
    @RequestMapping("/logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "index";
    }
}
