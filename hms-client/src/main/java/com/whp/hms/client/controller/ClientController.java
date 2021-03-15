package com.whp.hms.client.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.service.ClientService;
import com.whp.hms.client.service.TempServiceService;
import com.whp.hms.client.utils.ModelUtils;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.Client;
import com.whp.hms.core.entity.TempService;
import com.whp.hms.core.util.CommonResponse;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/hms/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Resource
    private ClientService service;
    @Resource
    private TempServiceService tempServiceService;

    /**
     * 获取当前客户信息
     */
    @ResponseBody
    @RequestMapping("clientInfo")
    public Object clientInfo() {
        try {
            logger.info("-------------获取当前客户信息-------------");
            return CommonResponse.success(ShiroUtil.getSubject().getPrincipal());
        } catch (Exception e) {
            logger.error("获取当前用户信息失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
    }


    /**
     * 跳转到我的信息页面
     */
    @RequestMapping("toMyInfo")
    public ModelAndView selfInfo(HttpServletRequest request) {
        ModelAndView result = ModelUtils.createModel(request, "/client/clientInfo");
        result.addObject("client", ShiroUtil.getSubject().getPrincipal());
        return result;
    }

    /**
     * 跳转到我的信息修改页面
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request) {
        ModelAndView result = ModelUtils.createModel(request, "/client/update");
        result.addObject("client", ShiroUtil.getSubject().getPrincipal());
        return result;
    }

    /**
     * 跳转到我的入住记录页面
     */
    @RequestMapping("toRecord")
    public ModelAndView toRecord(HttpServletRequest request) {
        ModelAndView result = ModelUtils.createModel(request, "/client/record");
        result.addObject("client", ShiroUtil.getSubject().getPrincipal());
        return result;
    }

    /**
     * 跳转到我的订房记录页面
     */
    @RequestMapping("toReserveRecord")
    public ModelAndView toReserveRecord(HttpServletRequest request) {
        ModelAndView result = ModelUtils.createModel(request, "/client/resRecord");
        result.addObject("client", ShiroUtil.getSubject().getPrincipal());
        return result;
    }


    /**
     * 修改用户个人信息
     */
    @ResponseBody
    @RequestMapping("update")
    public Object editMySelf(@RequestBody String jsonBody) {
        try {
            logger.info("-------------前台修改客户个人信息-------------");
            JSONObject params = JSON.parseObject(jsonBody);

            Client client = (Client) ShiroUtil.getSubject().getPrincipal();
            if (params.containsKey("email")) {
                client.setEmail(params.getString("email"));
            }
            if (params.containsKey("nickname")) {
                client.setNickname(params.getString("nickname"));
            }
            if (params.containsKey("phone")) {
                client.setPhone(params.getString("phone"));
            }
            if (params.containsKey("identify")) {
                client.setIdentify(params.getString("identify"));
            }
            if (!StringUtils.isEmpty(params.getString("pwd"))) {
                client.setPassword(ShiroUtil.salt(params.getString("pwd"), client.getSalt()));
            }

            Client result = service.update(client);
            if (result == null) {
                return CommonResponse.error("修改失败");
            }
            return CommonResponse.success("修改成功!");
        } catch (Exception e) {
            logger.error("修改客户个人信息失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
    }

    /**
     * 呼叫服务
     */
    @ResponseBody
    @RequestMapping("insertTemp")
    public Object insertTemp(@RequestBody TempService tempService) {
        try {
            logger.info("-------------呼叫服务-------------");
            tempService.setState(0);
            tempService.setCreateTime(DateUtil.getNowTime());
            if (tempServiceService.insert(tempService) != null) {
                return Response.success("");
            }
            return Response.error("呼叫失败");
        } catch (Exception e) {
            logger.error("呼叫服务失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
