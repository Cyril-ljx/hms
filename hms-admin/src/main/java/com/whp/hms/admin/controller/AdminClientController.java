package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.ClientService;
import com.whp.hms.admin.utils.ShiroUtil;
import com.whp.hms.core.entity.Client;
import com.whp.hms.core.util.DateUtil;
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
@RequestMapping("/hms/admin/client")
public class AdminClientController {
    private static final Logger logger = LoggerFactory.getLogger(AdminClientController.class);

    @Resource
    private ClientService service;


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname, Integer state) {
        try {
            logger.info("-------------查询所有客户信息-------------");
            List<Client> clients = service.list((page - 1) * limit, limit, nickname, state);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryCount(nickname, state));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有客户失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取客户详情信息
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------获取客户详情信息-------------");
            Client client = service.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (client != null) {
                return Response.success(client);
            }
            return Response.error("获取失败");
        } catch (Exception e) {
            logger.error("获取客户详情信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 注册客户信息
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Client client) {
        try {
            logger.info("-------------注册客户信息-------------");

            Client result = service.queryByUserName(client.getUsername());
            if (result != null) {
                return Response.error("该账号已存在,请换个账号注册!谢谢合作!");
            }
            client.setState(1);
            client.setCreateTime(DateUtil.getNowTime());
            client.setSalt(ShiroUtil.createSalt());
            client.setPassword(ShiroUtil.salt(client.getPassword(), client.getSalt()));

            client = service.insert(client);
            if (client != null) {
                return Response.success(client);
            }
            return Response.error("注册失败");
        } catch (Exception e) {
            logger.error("注册客户信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


    /**
     * 修改户信息
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object update(@RequestBody Client client) {
        try {
            logger.info("-------------修改户信息-------------");
            if (!StringUtils.isEmpty(client.getPassword())) {
                client.setPassword(ShiroUtil.salt(client.getPassword(), client.getSalt()));
            }
            client = service.update(client);
            if (client != null) {
                return Response.success(client);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改户信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除客户信息
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除客户信息-------------");
            boolean result = service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (result) {
                return Response.success("删除成功");
            }
            return Response.error("删除失败");
        } catch (Exception e) {
            logger.error("删除客户信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
