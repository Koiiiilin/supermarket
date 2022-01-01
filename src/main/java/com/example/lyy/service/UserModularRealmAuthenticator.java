package com.example.lyy.service;

import com.example.lyy.type.UserToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;

import org.apache.shiro.realm.Realm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("UserModularRealmAuthenticator:method doAuthenticate() execute ");
        assertRealmsConfigured();
        UserToken userToken = (UserToken) authenticationToken;
        String loginType = userToken.getLoginType();
        Collection<Realm> realms = getRealms();
        System.out.println(realms);
        List<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType)) {
                System.out.println(realm.getName()+" "+loginType);
                typeRealms.add(realm);
            }
        }
        if (typeRealms.size() == 1){
            System.out.println("doSingleRealmAuthentication() execute ");
            return doSingleRealmAuthentication(typeRealms.get(0), userToken);
        }
        else{
            System.out.println("doMultiRealmAuthentication() execute ");
            return doMultiRealmAuthentication(typeRealms, userToken);
        }
    }

}
