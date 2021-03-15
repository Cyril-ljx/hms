package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.LiveRecordService;
import com.whp.hms.admin.service.ReserveRecordService;
import com.whp.hms.core.entity.LiveRecord;
import com.whp.hms.core.entity.ReserveRecord;
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
@RequestMapping("/hms/admin/record")
public class AdminClientRecordController {
    private static final Logger logger = LoggerFactory.getLogger(AdminClientRecordController.class);

    @Resource
    private LiveRecordService liveRecordService;


    @Resource
    private ReserveRecordService reserveRecordService;


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/liveList")
    public LayuiVO liveList(Integer page, Integer limit, String nickname, String room) {
        try {
            logger.info("-------------查询所有入住记录信息-------------");
            List<LiveRecord> clients = liveRecordService.queryAdminList((page - 1) * limit, limit, nickname, room);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(liveRecordService.queryAdminCount(nickname, room));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有通知信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("live/del")
    public Object liveDel(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除记录-------------");
            return Response.success(liveRecordService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除记录失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("reserve/del")
    public Object reserveDel(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除记录-------------");
            return Response.success(reserveRecordService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除记录失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/reserveList")
    public LayuiVO reserveList(Integer page, Integer limit, String nickname, String room) {
        try {
            logger.info("-------------查询所有订房记录信息-------------");
            List<ReserveRecord> clients = reserveRecordService.queryAdminList((page - 1) * limit, limit, nickname, room);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(reserveRecordService.queryAdminCount(nickname, room));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有通知信息失败:{}", e.getMessage());
            return null;
        }
    }
}
