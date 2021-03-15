package com.whp.hms.admin.controller;

import com.whp.hms.admin.service.LiveRecordService;
import com.whp.hms.admin.service.ReserveRecordService;
import com.whp.hms.admin.service.RoomOrderService;
import com.whp.hms.admin.service.RoomService;
import com.whp.hms.core.entity.RoomOrder;
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
@RequestMapping("/hms/admin/order")
public class AdminOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    @Resource
    private RoomOrderService service;

    @Resource
    private ReserveRecordService reserveRecordService;

    @Resource
    private LiveRecordService liveRecordService;

    @Resource
    private RoomService roomService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, Integer state, Integer typeId, String roomNum, String nickname) {
        try {
            logger.info("-------------查询所有订房订单信息-------------");
            List<RoomOrder> clients = service.list((page - 1) * limit, limit, state, typeId, roomNum, nickname);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.listCount(state, typeId, roomNum, nickname));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有订房订单信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/liveList")
    public LayuiVO livList(Integer page, Integer limit, Integer typeId, String roomNum, String nickname) {
        try {
            logger.info("-------------查询所有订房订单信息-------------");
            List<RoomOrder> clients = service.liveList((page - 1) * limit, limit, typeId, roomNum, nickname);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.liveListCount(typeId, roomNum, nickname));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有订房订单信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 入住
     */
    @ResponseBody
    @RequestMapping("live")
    public Object live(@RequestBody String jsonBody) {
        try {
            logger.info("-------------入住-------------");
            return service.live(jsonBody);
        } catch (Exception e) {
            logger.error("入住失败：" + e.getMessage());
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
            return service.cancel(jsonBody);
        } catch (Exception e) {
            logger.error("取消订单失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 结算
     */
    @ResponseBody
    @RequestMapping("close")
    public Object close(@RequestBody String jsonBody) {
        try {
            logger.info("-------------结算-------------");
            return service.close(jsonBody);
        } catch (Exception e) {
            logger.error("结算失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 退房
     */
    @ResponseBody
    @RequestMapping("stop")
    public Object stop(@RequestBody String jsonBody) {
        try {
            logger.info("-------------退房-------------");
            return service.stop(jsonBody);
        } catch (Exception e) {
            logger.error("退房失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/cancelList")
    public LayuiVO cancelList(Integer page, Integer limit, Integer typeId, String roomNum, String nickname) {
        try {
            logger.info("-------------查询所有订房订单信息-------------");
            List<RoomOrder> clients = service.cancelList((page - 1) * limit, limit, typeId, roomNum, nickname);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.cancelListCount(typeId, roomNum, nickname));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有订房订单信息失败:{}", e.getMessage());
            return null;
        }
    }
}
