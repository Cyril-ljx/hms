package com.whp.hms.admin.config;

import com.whp.hms.admin.service.ManagerService;
import com.whp.hms.admin.utils.ShiroUtil;
import com.whp.hms.core.entity.Manager;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ManagerRealm extends AuthorizingRealm {

    @Autowired
    private ManagerService service;

    /**
     * 权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Manager client = (Manager) principals.getPrimaryPrincipal();
        //获取角色权限
        String permissionList = client.getPermission();
        //添加角色权限
        List<String> permissions = Arrays.asList(permissionList.split(","));
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
        String username = uptoken.getUsername();
        Manager sysUser = service.queryByUserName(username);
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
