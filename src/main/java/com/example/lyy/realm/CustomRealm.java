package com.example.lyy.realm;

import com.example.lyy.dao.UserMapper;
import com.example.lyy.pojo.Perms;
import com.example.lyy.pojo.User;
//import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("----------验证权限---------");
        //获取身份信息
        User Principal = (User) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证: "+Principal);
        User user = userMapper.findRolesByUserName(Principal.getUserCode());
        System.out.println("user:"+user.getRoles());
        //授权角色信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            System.out.println(user.getRoles());
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName()); //添加角色信息
                //权限信息
                List<Perms> perms = userMapper.findPermsByRoleId(role.getId());
//                System.out.println("perms:"+perms);
//                perms.forEach(perms1 ->
//                        System.out.println(perms1.getName()+"-----"+perms1.getUrl()));
                if(!CollectionUtils.isEmpty(perms) && perms.get(0)!=null ){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getUrl());
//                        System.out.println(simpleAuthorizationInfo.getStringPermissions());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------获取认证信息------------");
        //        获取token的身份（账号）
        String username = (String) authenticationToken.getPrincipal();
        User userByName = userMapper.findUserByName(username);
        System.out.println("User:"+userByName);
        if (!ObjectUtils.isEmpty(userByName)){//数据库中包含
            SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(userByName,
                    userByName.getUserPassword(),
                    ByteSource.Util.bytes(userByName.getSalt()),
                    this.getName());
            SecurityUtils.getSubject().getSession().setAttribute("userinfo", userByName);
            return simpleAuthorizationInfo;
        }
        return null;
    }
}
