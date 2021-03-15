package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.TempServiceService;
import com.whp.hms.core.entity.RoomType;
import com.whp.hms.core.entity.TempService;
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

@Controller
@RequestMapping("/hms/admin/service")
public class AdminServiceController {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceController.class);

    @Resource
    private TempServiceService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO departmentList(Integer page, Integer limit, Integer state) {
        try {
            logger.info("-------------查询所有临时服务信息-------------");
            List<TempService> clients = service.list((page - 1) * limit, limit, state);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount(state));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有临时服务失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除-------------");
            boolean result = service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (result) {
                return Response.success("删除成功");
            }
            return Response.error("删除失败");
        } catch (Exception e) {
            logger.error("删除失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改-------------");

            TempService tempService = new TempService();
            tempService.setId(JSONObject.parseObject(jsonBody).getInteger("id"));
            tempService.setState(1);
            tempService = service.update(tempService);
            logger.info("-----------1------");


            if (tempService != null) {
                return Response.success(tempService);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
