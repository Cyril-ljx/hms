package com.whp.hms.admin.controller;

import com.whp.hms.admin.utils.ModelUtils;
import com.whp.hms.core.vo.Response;
import com.whp.hms.mapper.ManagerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hms/admin/api")
public class ApiController {

    @Resource
    private ManagerDao dao;
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);


    /**
     * Demo
     */
    @ResponseBody
    @RequestMapping("test")
    public Object test(String username) {
        try {
            logger.info("-------------Demo-------------");
            return dao.queryByUserName(username);
        } catch (Exception e) {
            logger.error("Demo失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("json")
    public Object data(String username) {
        try {
            logger.info("-------------Demo-------------");
            return dao.queryByUserName(username);
        } catch (Exception e) {
            logger.error("Demo失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}
