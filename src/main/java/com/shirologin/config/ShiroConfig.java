package com.shirologin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author pengyong
 * @date 2020/5/12 - 8:34
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /*
     * shiroFilterFactoryBea 3： */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器  常用过滤器有：
        /*shiro内置过滤器，可以实现权限相关的拦截
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user：如果使用rememberMe，可以直接访问
         *
         * perms:该资源必须授予资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         * */

        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        //添加过滤器
        //filterMap.put("/chat", "authc");
        //filterMap.put("/inside", "authc");
        filterMap.put("/index", "anon"); //放行index请求
        filterMap.put("/logingo", "anon"); //放行login请求
        filterMap.put("/logout", "anon");

        //授权过滤器
        filterMap.put("/chat", "perms[chat]");//拦截，需授权
        filterMap.put("/inside", "perms[inside]");//拦截，需授权
        filterMap.put("/*", "authc");//拦截，需认证

        shiroFilterFactoryBean.setLoginUrl("/login");//设置未认证跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");//设置未授权跳转的页面

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /*
     * DefaultWebSecurityManager 2：*/

    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {//Qualifier注解找到下面方法的userRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;
    }


    /*
     * 创建realm 1：*/
    @Bean("userRealm")  //方法返回的对象放入spring环境，以便上面方法使用
    public UserRealm getRealm() {
        return new UserRealm();
    }

    /*
     * 配置shiroDialect，用于thymeleaf和shiro标签配合使用
     * */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
