package com.whp.hms.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.service.RoomOrderService;
import com.whp.hms.client.service.RoomService;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.core.entity.RoomOrder;
import com.whp.hms.core.util.CommonResponse;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 19399.
 * @date 2021/2/22.
 * @time 13:01
 */
@Controller
@RequestMapping("/hms/client/roomOrder")
public class ClientRoomOrderController {

    private static final Logger logger = LoggerFactory.getLogger(ClientRoomOrderController.class);
    @Resource
    private RoomOrderService roomOrderService;

    /**
     * 订购房间
     */
    @ResponseBody
    @RequestMapping("reserveRoom")
    public Object reserveRoom(@RequestBody RoomOrder roomOrder) {
        try {
            logger.info("-------------订购房间-------------");
            return roomOrderService.insert(roomOrder);
        } catch (Exception e) {
            logger.error("订购房间失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 取消订单
     */
    @ResponseBody
    @RequestMapping("cancel")
    public Object cancel(@RequestBody String jsonBody) {
        try {
            logger.info("-------------取消订单-------------");
            return roomOrderService.cancel(jsonBody);
        } catch (Exception e) {
            logger.error("取消订单失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取我的订单
     */
    @ResponseBody
    @RequestMapping("/myRoomOrder")
    public Object myComment(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取我的订单-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            Integer page = jsonObject.getInteger("page");
            Integer limit = jsonObject.getInteger("limit");
            Integer id = ShiroUtil.getUserId();

            List<RoomOrder> roomOrderList = roomOrderService.myroomOrdertList((page - 1) * limit, limit, id);
            boolean isLast = false;
            if (roomOrderList != null) {
                if (roomOrderList.size() != limit) {
                    isLast = true;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("result", roomOrderList);
                map.put("isLast", isLast);
                return Response.success(map);
            }
            return CommonResponse.error("获取失败");
        } catch (Exception e) {
            logger.error("获取我的订单失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
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
            boolean result = roomOrderService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            return result ? Response.success("") : Response.error("删除失败");
        } catch (Exception e) {
            logger.error("删除失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 当前订房订单
     */
    @ResponseBody
    @RequestMapping("nowOrder")
    public Object nowOrder() {
        try {
            logger.info("-------------当前订房订单-------------");
            return roomOrderService.nowOrder();
        } catch (Exception e) {
            logger.error("当前订房订单失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
