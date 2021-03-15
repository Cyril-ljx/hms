package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.NoticeService;
import com.whp.hms.core.entity.Notice;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.core.util.LayuiVO;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告栏controller
 */
@Controller
@RequestMapping("/hms/admin/advisement")
public class AdminAdvisementController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAdvisementController.class);

    @Resource
    private NoticeService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有通知信息-------------");
            List<Notice> clients = service.queryAdminList((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryAdminCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有通知信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除公告
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除公告-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 增加公告
     */
    @ResponseBody
    @RequestMapping("insert")
    public Object insert(@RequestBody Notice notice) {
        try {
            logger.info("-------------添加公告-------------");
            notice.setCreateTime(DateUtil.getNowTime());
            notice.setIsShow(0);
            Notice result = service.insert(notice);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------详情-------------");
            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改公告
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Notice notice) {
        try {
            logger.info("-------------修改公告-------------");
            notice.setCreateTime(DateUtil.getNowTime());
            Notice result = service.update(notice);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 首显公告
     */
    @ResponseBody
    @RequestMapping("isShow")
    public Object isShow(@RequestBody String jsonBody) {
        try {
            logger.info("-------------首显公告-------------");
            return Response.success(service.showById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("修改公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}