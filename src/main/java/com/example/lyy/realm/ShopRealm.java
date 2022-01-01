package com.example.lyy.realm;

import com.example.lyy.dao.ShopMapper;
import com.example.lyy.pojo.Shopper;
import com.example.lyy.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class ShopRealm extends AuthorizingRealm {
    @Autowired
    ShopMapper shopMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------获取ShopRealm认证信息------------");
        //        获取token的身份（账号）
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(username);
        Shopper shopper = shopMapper.findShopperByname(username);
        System.out.println("Shop:"+shopper);
        if (!ObjectUtils.isEmpty(shopper)){//数据库中包含
            SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(shopper,
                    shopper.getPwd(),
                    ByteSource.Util.bytes(shopper.getSalt()),
                    this.getName());
            SecurityUtils.getSubject().getSession().setAttribute("shopInfo", shopper);
            return simpleAuthorizationInfo;
        }
        return null;
    }
}
