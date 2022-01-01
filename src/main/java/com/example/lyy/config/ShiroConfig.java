package com.example.lyy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import com.example.lyy.realm.CustomRealm;
import com.example.lyy.realm.ShopRealm;
import com.example.lyy.service.UserModularRealmAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class ShiroConfig {
    //1.创建shiroFilter  //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map = new HashMap<String,String>();
//        配置匿名过滤器，放行静态资源
        map.put("/images/**","anon");//anon 设置为公共资源
        map.put("/js/**","anon");//anon 设置为公共资源
        map.put("/css/**","anon");//anon 设置为公共资源
        map.put("/bootstrap-3.3.7-dist/**","anon");//anon 设置为公共资源
//      登录界面放行
        map.put("/sendToLogin","anon");//anon 设置为公共资源
        map.put("/user/dologin","anon");//anon 设置为公共资源
//        退出认证过滤器
        map.put("logout","logout");
//          配置表单认证过滤器
        map.put("/frame","authc");
        map.put("/bill/findBills","perms[bill:list]");
        map.put("/pro/findPros","perms[user:list]");
        map.put("/user/findAllByPage","perms[user:list]");
        map.put("/user/sendToChange","perms[user:pwd]");
        map.put("/sendToAdd","perms[user:add]");
        map.put("/mail","perms[user:email]");
        map.put("/pro/sendToAdd","perms[provider:save]");
        map.put("/pro/del_pro","perms[provider:delete]");
        map.put("/pro/update_pro","perms[provider:update]");
        map.put("/bill/sendToAdd","perms[bill:save]");
        map.put("/bill/update_bill","perms[bill:update]");
//        map.put("/**","user");
        //默认认证界面路径---当认证不通过时跳转
        shiroFilterFactoryBean.setLoginUrl("/sendToLogin");
//        填充权限
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized.html");
        return shiroFilterFactoryBean;
    }
    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());
        Set<Realm> realms  = new HashSet<>();
        realms.add(getRealm());
        realms.add(getShopRealm());
        //给安全管理器设置
        defaultWebSecurityManager.setRealms(realms);
        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomRealm customerRealm = new CustomRealm();
        System.out.println("--------------getRealm()方法");
        //设置hashed凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5加密
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
    @Bean
    public Realm getShopRealm(){
        ShopRealm shopRealm = new ShopRealm();
        System.out.println("--------------getShopRealm()方法");
        //设置hashed凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5加密
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        shopRealm.setCredentialsMatcher(credentialsMatcher);
        return shopRealm;
    }
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
