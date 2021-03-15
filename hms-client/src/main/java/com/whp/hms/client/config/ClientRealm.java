package com.whp.hms.client.config;

import com.whp.hms.client.service.ClientService;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.Client;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/2/20.
 * @time 13:08
 */
public class ClientRealm extends AuthorizingRealm {
    @Autowired
    private ClientService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
        String username = uptoken.getUsername();
        Client sysUser = service.queryByUserName(username);
        if (sysUser == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (sysUser.getState() != 1) {
            throw new AuthenticationException("用户已冻结");
        }
        String password = ShiroUtil.salt(uptoken.getPassword(), sysUser.getSalt());
        if (!sysUser.getPassword().equals(password)) {
            throw new AuthenticationException("密码错误");
        }
        return new SimpleAuthenticationInfo(sysUser, uptoken.getPassword(), getName());
    }
    }

