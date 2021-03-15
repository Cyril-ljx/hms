package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.RoomService;
import com.whp.hms.admin.utils.ShiroUtil;
import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.Room;
import com.whp.hms.core.entity.RoomType;
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
import java.util.List;

@Controller
@RequestMapping("/hms/admin/room")
public class AdminRoomController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRoomController.class);


    @Resource
    private RoomService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO departmentList(Integer page, Integer limit, Integer state, Integer use, Integer typeId) {
        try {
            logger.info("-------------查询所有房间信息-------------");
            List<Room> clients = service.list((page - 1) * limit, limit, state, use, typeId);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount(state, use, typeId));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有房间失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 房间详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------房间详情-------------");
            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("房间详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加房间
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Room room) {
        try {
            logger.info("-------------添加房间-------------");
            Room result = service.queryByRoomNum(room.getRoomNum());
            if (result != null) {
                return Response.error("房间编号已存在!");
            }
            room.setUseable(0);
            room.setState(0);
            room = service.insert(room);
            if (room != null) {
                return Response.success(room);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加房间失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改房间信息
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody Room room) {
        try {
            logger.info("-------------修改房间信息-------------");
            Room result = service.queryByRoomNum(room.getRoomNum());
            if (result != null) {
                return Response.error("房间编号已存在!");
            }
            if (service.update(room) != null) {
                return Response.success("");
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改房间信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改房间信息
     */
    @ResponseBody
    @RequestMapping("stop")
    public Object stop(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改房间信息-------------");
            Room room = new Room();

            room.setId(JSONObject.parseObject(jsonBody).getInteger("id"));
            room.setUseable(0);

            if (service.update(room) != null) {
                return Response.success("");
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改房间信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改房间信息
     */
    @ResponseBody
    @RequestMapping("use")
    public Object use(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改房间信息-------------");
            Room room = new Room();

            room.setId(JSONObject.parseObject(jsonBody).getInteger("id"));
            room.setUseable(1);

            if (service.update(room) != null) {
                return Response.success("");
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改房间信息失败：" + e.getMessage());
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
}
