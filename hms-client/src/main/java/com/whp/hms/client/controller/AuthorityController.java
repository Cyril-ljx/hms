package com.whp.hms.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.service.InfoModelService;
import com.whp.hms.client.service.NoticeService;
import com.whp.hms.client.service.RoomService;
import com.whp.hms.client.service.RoomTypeService;
import com.whp.hms.client.utils.ModelUtils;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.InfoModel;
import com.whp.hms.core.entity.Notice;
import com.whp.hms.core.entity.Room;
import com.whp.hms.core.util.CommonResponse;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 19399.
 * @date 2021/2/22.
 * @time 21:21
 */
@Controller
@RequestMapping("/hms/client/authority")
public class AuthorityController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Resource
    private NoticeService service;

    @Resource
    private InfoModelService infoModelService;

    @Resource
    private RoomTypeService typeService;

    @Resource
    private RoomService roomService;

    /**
     * 获取首显的公告
     */
    @ResponseBody
    @RequestMapping("/isShow")
    public Object selectAll() {
        try {
            logger.info("-------------获取首显的通知信息-------------");
            Notice notice = service.queryByShow();
            if (notice != null) {
                return Response.success(notice);
            }
            return Response.error("查询失败");
        } catch (Exception e) {
            logger.info("查询首显的信息失败:{}", e.getMessage());
            return null;
        }
    }


    /**
     * 获取正在使用的info_model
     */
    @ResponseBody
    @RequestMapping("/isUse")
    public Object myComment() {
        try {
            logger.info("-------------获取正在使用的酒店模板-------------");

            InfoModel infoModelListList = infoModelService.isUseList();
            if (infoModelListList != null) {
                return CommonResponse.success(infoModelListList);
            }
            return CommonResponse.error("获取失败");
        } catch (Exception e) {
            logger.error("获取正在使用的模板失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
    }

    /**
     * 获取所有房间类型
     */
    @ResponseBody
    @RequestMapping("types")
    public Object types() {
        try {
            logger.info("-------------获取所有房间类型-------------");
            return Response.success(typeService.getAll());
        } catch (Exception e) {
            logger.error("获取所有房间类型失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/index");
    }

    /**
     * 跳转到房间详情
     */
    @RequestMapping("detail")
    public ModelAndView detail(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "detail");
        result.addObject("id", id);
        return result;
    }


    /**
     * 跳转到公告栏
     */
    @RequestMapping("advisementList")
    public ModelAndView advisementList(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/advisementList");
    }

    /**
     * 获取公告
     */
    @ResponseBody
    @RequestMapping("/allInfo")
    public Object myRecord(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取公告-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            Integer page = jsonObject.getInteger("page");
            Integer limit = jsonObject.getInteger("limit");

            List<Notice> reserveRecordList = service.queryAllByLimit((page - 1) * limit, limit);
            Map<String, Object> map = new HashMap<>();
            if (reserveRecordList != null) {
                if (reserveRecordList.size() != limit) {
                    map.put("isLast", true);
                } else {
                    map.put("isLast", false);
                }
                map.put("result", reserveRecordList);
                return Response.success(map);
            }
            return CommonResponse.error("获取失败");
        } catch (Exception e) {
            logger.error("获取公告失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
    }

    /**
     * 房间详情
     */
    @ResponseBody
    @RequestMapping("room/detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------房间详情-------------");
            return roomService.detail(jsonBody);
        } catch (Exception e) {
            logger.error("房间详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 订房
     */
    @ResponseBody
    @RequestMapping("list")
    public Object list(@RequestBody String jsonBody) {
        try {
            logger.info("-------------房间搜索-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            Integer page = jsonObject.getInteger("page");
            Integer limit = jsonObject.getInteger("limit");
            Integer typeId = jsonObject.getInteger("type_id");
            BigDecimal start = jsonObject.getBigDecimal("start");
            BigDecimal end = jsonObject.getBigDecimal("end");
            List<Room> result = roomService.query((page - 1) * limit, limit, typeId, start, end);
            boolean isLast = false;
            Map<String, Object> map = new HashMap<>();
            if (result != null) {
                if (result.size() != limit) {
                    isLast = true;
                }
                map.put("result", result);
                map.put("isLast", isLast);
                return Response.success(map);
            }
            return Response.error("房间搜索失败");
        } catch (Exception e) {
            logger.error("房间搜索失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
