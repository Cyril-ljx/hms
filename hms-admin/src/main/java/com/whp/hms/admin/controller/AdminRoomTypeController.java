package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.RoomTypeService;
import com.whp.hms.core.entity.RoomType;
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
@RequestMapping("/hms/admin/roomType")
public class AdminRoomTypeController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRoomTypeController.class);


    @Resource
    private RoomTypeService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO departmentList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有房间类型信息-------------");
            List<RoomType> clients = service.queryAllByLimit((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有房间类型失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody RoomType type) {
        try {
            logger.info("-------------添加-------------");
            type = service.update(type);
            if (type != null) {
                return Response.success(type);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改失败：" + e.getMessage());
            return Response.error(e.getMessage());
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
     * 添加
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody RoomType type) {
        try {
            logger.info("-------------添加-------------");
            type = service.insert(type);
            if (type != null) {
                return Response.success(type);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 房间类型详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------房间类型详情-------------");
            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("房间类型详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所房间类型
     */
    @ResponseBody
    @RequestMapping("roomTypes")
    public Object roomTypes() {
        try {
            logger.info("-------------获取所房间类型-------------");
            return Response.success(service.queryAll());
        } catch (Exception e) {
            logger.error("获取所房间类型失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}
