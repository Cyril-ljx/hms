package com.whp.hms.admin.controller;

import com.whp.hms.admin.service.ImgLogService;
import com.whp.hms.admin.service.LoginLogService;
import com.whp.hms.core.entity.ImgLog;
import com.whp.hms.core.entity.LoginLog;
import com.whp.hms.core.util.CommonResponse;
import com.whp.hms.core.util.LayuiVO;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志控制器
 */
@Controller
@RequestMapping("/hms/admin/log")
public class AdminLogController {

    private static final Logger logger = LoggerFactory.getLogger(AdminLogController.class);


    @Resource
    private LoginLogService service;
    @Resource
    private ImgLogService imgLogService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/loginList")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有登录日志信息-------------");
            List<LoginLog> clients = service.queryAdminList((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryAdminCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有登录日志信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/imgList")
    public LayuiVO imgList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有图片上传日志信息-------------");
            List<ImgLog> clients = imgLogService.queryAdminList((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(imgLogService.queryAdminCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有图片上传日志信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 全部删除登录日记
     */
    @ResponseBody
    @RequestMapping("delAll")
    public Object delAll() {
        try {
            logger.info("-------------全部删除日记-------------");
            boolean result = service.deleteAll();
            if (result) {
                return CommonResponse.success("删除成功");
            }
            return CommonResponse.error("删除失败");
        } catch (Exception e) {
            logger.error("删除日记失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 批量删除登录日记
     */
    @ResponseBody
    @RequestMapping("batchDelete")
    public Object batchDelete(@RequestBody String ids) {
        try {
            logger.info("-------------全部删除日记-------------");
            if (StringUtils.isEmpty(ids)) {
                return Response.error("无id传入");
            }
            /*if (ids == null || ids.isEmpty()) {
                return Response.error("无id传入");
            }*/
            String[] id = ids.split(",");
            List<Integer> allId = new ArrayList<>();
            for (int i = 0; i < id.length; i++) {
                allId.add(Integer.valueOf(id[i]));
            }
            boolean result = service.batchDelete(allId);
            if (result) {
                return CommonResponse.success("删除成功");
            }
            return CommonResponse.error("删除失败");
        } catch (Exception e) {
            logger.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 全部删除图片日记
     */
    @ResponseBody
    @RequestMapping("delAllImg")
    public Object delAllImg() {
        try {
            logger.info("-------------全部删除照片日记-------------");
            boolean result = imgLogService.delAllImg();
            if (result) {
                return CommonResponse.success("删除成功");
            }
            return CommonResponse.error("删除失败");
        } catch (Exception e) {
            logger.error("删除日记失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 批量删除图片日记
     */
    @ResponseBody
    @RequestMapping("batchDelImg")
    public Object batchDelImg(@RequestBody String ids) {
        try {
            logger.info("-------------批量删除照片日记-------------");
            if (StringUtils.isEmpty(ids)) {
                return Response.error("无id传入");
            }
           /* if (ids == null || ids.isEmpty()) {
                return Response.error("无id传入");
            }*/
            String[] id = ids.split(",");
            List<Integer> allId = new ArrayList<>();
            for (int i = 0; i < id.length; i++) {
                allId.add(Integer.valueOf(id[i]));
            }
            boolean result = imgLogService.batchDelImg(allId);
            if (result) {
                return CommonResponse.success("删除成功");
            }
            return CommonResponse.error("删除失败");
        } catch (Exception e) {
            logger.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
