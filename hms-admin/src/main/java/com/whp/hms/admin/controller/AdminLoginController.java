package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.LoginLogService;
import com.whp.hms.admin.utils.LoginLogFactory;
import com.whp.hms.admin.utils.ShiroUtil;
import com.whp.hms.core.vo.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 登录控制器
 */
@Controller
@RequestMapping("/hms/admin/")
public class AdminLoginController {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

    @Resource
    private LoginLogService service;

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping("login")
    public Object login(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            logger.info("-------------登录-------------");
            JSONObject params = (JSONObject) JSONObject.parse(jsonBody);
            String account = params.getString("username");
            String pwd = params.getString("pwd");

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);

            //shiro验证登录
            subject.login(token);
            service.insert(LoginLogFactory.success(ShiroUtil.getUserId()));
            return Response.success("ok");
        } catch (Exception e) {
            service.insert(LoginLogFactory.error(e.getMessage()));
            logger.error("登录失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        service.insert(LoginLogFactory.logout(ShiroUtil.getUserId(), request.getRemoteAddr()));
        ShiroUtil.getSubject().logout();
        return "/login";
    }
}
