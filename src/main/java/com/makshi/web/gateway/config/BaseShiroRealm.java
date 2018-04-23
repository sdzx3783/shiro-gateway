package com.makshi.web.gateway.config;

import com.makshi.web.gateway.security.model.LoginUser;
import com.makshi.web.gateway.security.model.SysPermission;
import com.makshi.web.gateway.security.model.SysRole;
import com.makshi.web.gateway.security.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class BaseShiroRealm  extends AuthorizingRealm{

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pricipals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        LoginUser loginUser = (LoginUser) pricipals.getPrimaryPrincipal();
        for(SysRole role:loginUser.getRoles()){
            authorizationInfo.addRole(role.getRole());
        }
        for(SysPermission permission:loginUser.getPermissions()){
            authorizationInfo.addStringPermission(permission.getPermission());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();

        LoginUser loginUser = userInfoService.findByUsername(username);

        if(loginUser == null) return null;

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                loginUser,
                loginUser.getPassword(),
               getName()
        );
        return authenticationInfo;
    }
}
