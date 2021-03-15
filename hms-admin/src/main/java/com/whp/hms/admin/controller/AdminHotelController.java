package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.InfoModelService;
import com.whp.hms.core.entity.InfoModel;
import com.whp.hms.core.entity.Notice;
import com.whp.hms.core.util.CommonResponse;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 酒店模板 控制器
 */
@Controller
@RequestMapping("/hms/admin/hotel")
public class AdminHotelController {

    private static final Logger logger = LoggerFactory.getLogger(AdminHotelController.class);
    @Resource
    private InfoModelService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有酒店信息模板信息-------------");
            List<InfoModel> clients = service.queryAllByLimit((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有酒店信息模板失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取详情-------------");
            InfoModel infoModel = service.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (infoModel != null) {
                return Response.success(infoModel);
            }
            return Response.error("无此数据");
        } catch (Exception e) {
            logger.error("获取详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除酒店信息
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除酒店信息-------------");
            InfoModel infoModel = service.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (infoModel.getIsUse() == 1) {
                return Response.error("该酒店信息模板正在被使用无法删除");
            }
            boolean result = service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (result) {
                return Response.success("删除酒店信息成功");
            }
            return Response.error("删除酒店信息失败");
        } catch (Exception e) {
            logger.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 增加酒店信息
     */
    @ResponseBody
    @RequestMapping("insert")
    public Object insert(@RequestBody InfoModel infoModel) {
        try {
            logger.info("-------------添加酒店信息-------------");
            infoModel.setIsUse(0);
            InfoModel result = service.insert(infoModel);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加酒店信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改酒店信息
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody InfoModel infoModel) {
        try {
            logger.info("-------------修改酒店信息-------------");
            InfoModel result = service.update(infoModel);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改酒店信息失败：" + e.getMessage());
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
            logger.info("-------------首显酒店信息-------------");
            return Response.success(service.showById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("首显酒店信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}
