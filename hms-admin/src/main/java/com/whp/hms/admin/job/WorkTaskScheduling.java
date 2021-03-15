package com.whp.hms.admin.job;

import com.whp.hms.admin.controller.AdminClientRecordController;
import com.whp.hms.admin.service.RoomOrderService;
import com.whp.hms.core.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

/**
 * @author 19399.
 * @date 2021/2/23.
 * @time 11:45
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class WorkTaskScheduling {
    private static final Logger logger = LoggerFactory.getLogger(AdminClientRecordController.class);
    @Autowired
    RoomOrderService roomOrderService;

    @Scheduled(cron = "0 0 4 * * ?")
    public void checkOutDate() {
        logger.info("----定时任务开始检测过期的订单----------");
        try {
            roomOrderService.outDate();
        } catch (Exception e) {
            logger.info("定时任务开始检测过期的订单失败:{}", e.getMessage());
        }
    }
}
