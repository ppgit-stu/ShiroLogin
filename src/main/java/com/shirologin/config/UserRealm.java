package com.shirologin.config;

import com.shirologin.entity.UserBean;
import com.shirologin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author pengyong
 * @date 2020/5/12 - 8:40
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {


    /*
    * 执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权。。。");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
        //info.addStringPermission("chat");

        //到数据库查询当前登录用户的授权字符串
        Subject subject = SecurityUtils.getSubject();//获取当前登录用户
        UserBean userBean=(UserBean)subject.getPrincipal();
        UserBean user=userService.findById(userBean.getId());

        info.addStringPermission(user.getPerms());

        return info;
    }

    /*
    * 执行认证逻辑*/
    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("执行认证。。。");

        //编写shiro判断逻辑，用户名和密码
        //1.判断用户名
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        UserBean userBean=userService.findByName(userToken.getUsername());
        if (userBean==null) {
            //用户名不存在
            return null;
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(userBean, userBean.getPassword(), "");
    }
}
