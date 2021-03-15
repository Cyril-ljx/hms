package com.whp.hms.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.client.service.ReserveRecordService;
import com.whp.hms.client.service.RoomOrderService;
import com.whp.hms.client.service.RoomService;
import com.whp.hms.client.utils.ShiroUtil;
import com.whp.hms.core.entity.ReserveRecord;
import com.whp.hms.core.entity.Room;
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
 * @date 2021/2/20.
 * @time 23:20
 */
@Controller
@RequestMapping("/hms/client/reserve")
public class ClientReserveRecordController {

    private static final Logger logger = LoggerFactory.getLogger(ClientReserveRecordController.class);

    @Resource
    private ReserveRecordService service;


    /**
     * 获取订房记录
     */
    @ResponseBody
    @RequestMapping("/myRecord")
    public Object myRecord(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取订房记录-------------");
            JSONObject jsonObject = JSONObject.parseObject(jsonBody);
            Integer page = jsonObject.getInteger("page");
            Integer limit = jsonObject.getInteger("limit");
            Integer id = ShiroUtil.getUserId();

            List<ReserveRecord> reserveRecordList = service.myRecordtList((page - 1) * limit, limit, id);
            boolean isLast = false;
            if (reserveRecordList != null) {
                if (reserveRecordList.size() != limit) {
                    isLast = true;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("result", reserveRecordList);
                map.put("isLast", isLast);
                return Response.success(map);
            }
            return CommonResponse.error("获取失败");
        } catch (Exception e) {
            logger.error("获取我的订房记录失败：" + e.getMessage());
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
            boolean result = service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            return result ? Response.success("") : Response.error("删除失败");
        } catch (Exception e) {
            logger.error("删除失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}
