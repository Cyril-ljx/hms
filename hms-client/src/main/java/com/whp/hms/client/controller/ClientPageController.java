package com.whp.hms.client.controller;


import com.whp.hms.client.utils.ModelUtils;
import com.whp.hms.client.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("/hms/client/page")
public class ClientPageController {
    private static final Logger logger = LoggerFactory.getLogger(ClientPageController.class);

    /**
     * 跳转到错误页面
     */
    @RequestMapping("error")
    public ModelAndView error(HttpServletRequest request) {
        logger.info("------------访问了error接口");
        return ModelUtils.createModel(request, "/error");
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        if (ShiroUtil.isUser()) {
            return ModelUtils.createModel(request, "/index");
        }
        return new ModelAndView("/login");
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("regist")
    public ModelAndView regist(HttpServletRequest request) {
        if (ShiroUtil.isUser()) {
            return ModelUtils.createModel(request, "/index");
        }
        return new ModelAndView("/regist");
    }


    /**
     * 跳转到我的消息
     */
    @RequestMapping("toMyInfo")
    public ModelAndView myInfo(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/userInfo");
    }

    /**
     * 跳转到我的消息
     */
    @RequestMapping("toNowOrder")
    public ModelAndView myNowOrder(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/myNowOrder");
    }

    /**
     * 跳转到我的住房记录
     */
    @RequestMapping("myLiveRecords")
    public ModelAndView liveRecords(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/myLiveRecord");
    }

    /**
     * 跳转到我的订房记录
     */
    @RequestMapping("myReserveRecords")
    public ModelAndView myReserveRecords(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/myReserveRecord");
    }

    /**
     * 跳转到我的订单
     */
    @RequestMapping("myOrder")
    public ModelAndView myOrder(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/myOrder");
    }

    /**
     * 跳转到我当前订房
     */
    @RequestMapping("myNowOrder")
    public ModelAndView nowOrder(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/myNowOrder");
    }

}
