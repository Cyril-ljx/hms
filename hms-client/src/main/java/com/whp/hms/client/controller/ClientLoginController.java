package com.whp.hms.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.service.ClientService;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.Client;
import com.whp.hms.core.util.DateUtil;
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
@RequestMapping("/hms/client/")
public class ClientLoginController {

    private static final Logger logger = LoggerFactory.getLogger(ClientLoginController.class);

    @Resource
    private ClientService clientService;

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
            return Response.success("ok");
        } catch (Exception e) {
            logger.error("登录失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout() {
        ShiroUtil.getSubject().logout();
        return "/login";
    }

    /**
     * 注册客户信息
     */
    @ResponseBody
    @RequestMapping("regist")
    public Object regist(@RequestBody Client client) {
        try {
            logger.info("-------------注册客户信息-------------");
            Client result = clientService.queryByUserName(client.getUsername());
            if (result != null) {
                return Response.error("该账号已存在,请换个账号注册!谢谢合作!");
            }
            String account = client.getUsername();
            String pwd = client.getPassword();

            Response response = clientService.insert(client);
            Subject subject = SecurityUtils.getSubject();
            if (response.isSucceed()) {
                UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);
                //shiro验证登录
                subject.login(token);
                return Response.success("ok");
            }
            return Response.error("注册失败");
        } catch (Exception e) {
            logger.error("注册客户信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
