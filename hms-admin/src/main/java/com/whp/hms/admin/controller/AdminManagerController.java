package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.ManagerService;
import com.whp.hms.admin.service.RoleService;
import com.whp.hms.admin.utils.ShiroUtil;
import com.whp.hms.core.entity.Manager;
import com.whp.hms.core.entity.Role;
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

/**
 * 管理员控制器
 */
@Controller
@RequestMapping("/hms/admin/manager")
public class AdminManagerController {

    private static final Logger logger = LoggerFactory.getLogger(AdminManagerController.class);

    @Resource
    private ManagerService service;

    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有管理员信息-------------");
            int uid = ShiroUtil.getUserId();
            List<Manager> clients = service.queryAdminList(uid, (page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryAdminCount(uid));//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有管理员信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 管理员详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------管理员详情-------------");
            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("管理员详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加管理员
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Manager manager) {
        try {
            logger.info("-------------添加管理员-------------");

            Manager result = service.queryByUserName(manager.getUsername());
            if (result != null) {
                return Response.error("账号已存在");
            }

            manager.setSalt(ShiroUtil.createSalt());
            manager.setPassword(ShiroUtil.salt(manager.getPassword(), manager.getSalt()));
            manager.setState(1);
            result = service.insert(manager);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加管理员失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改管理员信息
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody Manager manager) {
        try {
            logger.info("-------------修改管理员信息-------------");

            if (!StringUtils.isEmpty(manager.getPassword())) {
                manager.setPassword(ShiroUtil.salt(manager.getPassword(), manager.getSalt()));
            }

            if (service.update(manager) != null) {
                return Response.success("");
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改管理员信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 重置密码
     */
    @ResponseBody
    @RequestMapping("reset")
    public Object reset(@RequestBody String jsonBody) {
        try {
            logger.info("-------------重置密码-------------");

            Manager manager = (Manager) ShiroUtil.getSubject().getPrincipal();
            String pwd = JSONObject.parseObject(jsonBody).getString("pwd");
            manager.setPassword(ShiroUtil.salt(pwd, manager.getSalt()));

            if (service.update(manager) != null) {
                return Response.success("");
            }
            return Response.error("重置密码失败");
        } catch (Exception e) {
            logger.error("重置密码失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


    /**
     * 删除管理员
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除管理员-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除管理员失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("role/add")
    public Object roleAdd(@RequestBody Role role) {
        try {
            logger.info("-------------添加角色-------------");
            if (roleService.insert(role) != null) {
                return Response.success("");
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("role/del")
    public Object roleDel(@RequestBody String jsonBody) {
        try {
            logger.info("-------------添加角色-------------");
            if (roleService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"))) {
                return Response.success("");
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有角色
     */
    @ResponseBody
    @RequestMapping("roles")
    public Object roles() {
        try {
            logger.info("-------------获取所有角色-------------");
            return Response.success(roleService.queryAll());
        } catch (Exception e) {
            logger.error("获取所有角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/roleList")
    public LayuiVO roleList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有角色信息-------------");
            List<Role> clients = roleService.queryAllByLimit((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(roleService.queryAllCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有角色信息失败:{}", e.getMessage());
            return null;
        }
    }
}
