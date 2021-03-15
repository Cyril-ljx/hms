package com.whp.hms.admin.controller;

import com.whp.hms.admin.utils.ModelUtils;
import com.whp.hms.admin.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("/hms/admin/page")
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    /**
     * 跳转到错误页面
     */
    @RequestMapping("error")
    public ModelAndView error(HttpServletRequest request) {
        logger.info("------------访问了error接口");
        return ModelUtils.createModel(request, "/error");
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        if (ShiroUtil.isUser()) {
            return ModelUtils.createModel(request, "/index");
        }
        return new ModelAndView("/login");
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/index");
    }


    /**
     * 跳转到欢迎页面
     */
    @RequestMapping("welcome")
    public ModelAndView welcome(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/welcome");
    }

    /**
     * 跳转到重置密码界面
     */
    @RequestMapping("reset")
    public ModelAndView reset(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/reset");
    }

    /**
     * 跳转到管理员管理页面
     */
    @RequestMapping("manager/index")
    public ModelAndView managerIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/manager/index");
    }

    /**
     * 修改管理员
     */
    @RequestMapping("manager/edit")
    public ModelAndView managerEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/system/manager/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 管理员添加-
     */
    @RequestMapping("manager/add")
    public ModelAndView managerAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/manager/add");
    }

    /**
     * 跳转到登录日志管理
     */
    @RequestMapping("loginLog/index")
    public ModelAndView logIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/loginlog/index");
    }

    /**
     * 跳转到图片上传日志管理
     */
    @RequestMapping("imgLog/index")
    public ModelAndView imgLog(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/imgLog/index");
    }

    /**
     * 跳转到酒店信息模板管理页面
     */
    @RequestMapping("hotel/index")
    public ModelAndView hotelIndex(HttpServletRequest request) {
        ModelAndView result = ModelUtils.createModel(request, "/system/hotel/index");
        return result;
    }

    /**
     * 模板信息添加
     */
    @RequestMapping("hotel/add")
    public ModelAndView hotelAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/hotel/add");
    }

    /**
     * 模板信息修改
     */
    @RequestMapping("hotel/edit")
    public ModelAndView hotelEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/system/hotel/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到角色管理页面
     */
    @RequestMapping("role/index")
    public ModelAndView roleIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/role/index");
    }

    /**
     * 角色添加
     */
    @RequestMapping("role/add")
    public ModelAndView roleAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/role/add");
    }


    /**
     * 跳转到公告栏管理页面
     */
    @RequestMapping("advisement/index")
    public ModelAndView adviseIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/advisement/index");
    }

    /**
     * 发布公告页面
     */
    @RequestMapping("advisement/add")
    public ModelAndView advisementAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/advisement/add");
    }


    /**
     * 修改公告
     */
    @RequestMapping("advisement/edit")
    public ModelAndView advisementEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/system/advisement/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到员工信息管理页面
     */
    @RequestMapping("staff/index")
    public ModelAndView staffIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/staff/index");
    }

    /**
     * 添加员工信息
     */
    @RequestMapping("staff/add")
    public ModelAndView staffAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/staff/add");
    }


    /**
     * 修改员工信息
     */
    @RequestMapping("staff/edit")
    public ModelAndView staffEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/staff/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到组织岗位管理页面
     */
    @RequestMapping("job/index")
    public ModelAndView jobIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/job/index");
    }

    /**
     * 添加岗位
     */
    @RequestMapping("job/add")
    public ModelAndView jobAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/job/add");
    }


    /**
     * 修改岗位信息
     */
    @RequestMapping("job/edit")
    public ModelAndView jobEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/job/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到部门管理页面
     */
    @RequestMapping("department/index")
    public ModelAndView departmentIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/department/index");
    }

    /**
     * 添加部门
     */
    @RequestMapping("department/add")
    public ModelAndView departmentAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/department/add");
    }

    /**
     * 修改部门
     */
    @RequestMapping("department/edit")
    public ModelAndView departmentEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/department/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到房间类型管理页面
     */
    @RequestMapping("roomType/index")
    public ModelAndView roomTypeIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/room/roomType/index");
    }

    /**
     * 添加房间类型
     */
    @RequestMapping("roomType/add")
    public ModelAndView roomTypeAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/room/roomType/add");
    }


    /**
     * 修改房间类型
     */
    @RequestMapping("roomType/edit")
    public ModelAndView roomTypeEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/room/roomType/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到房间管理页面
     */
    @RequestMapping("room/index")
    public ModelAndView roomIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/room/room/index");
    }

    /**
     * 房间添加
     */
    @RequestMapping("room/add")
    public ModelAndView roomAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/room/room/add");
    }


    /**
     * 修改房间页面
     */
    @RequestMapping("room/edit")
    public ModelAndView roomEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/room/room/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到客户管理页面
     */
    @RequestMapping("userInfo/index")
    public ModelAndView userInfoIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/userInfo/index");
    }

    /**
     * 跳转到客户添加
     */
    @RequestMapping("userInfo/add")
    public ModelAndView userEdit(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/userInfo/add");
    }

    /**
     * 修改客户信息
     */
    @RequestMapping("userInfo/edit")
    public ModelAndView userInfoEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/user/userInfo/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 客户信息
     */
    @RequestMapping("userInfo/detail")
    public ModelAndView userInfoDetail(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/user/userInfo/detail");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到客户入住记录
     */
    @RequestMapping("liveRecord/index")
    public ModelAndView liveRecord(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/live_record/index");
    }

    /**
     * 跳转到客户
     */
    @RequestMapping("orderRecord/index")
    public ModelAndView orderRecord(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/user/order_record/index");
    }

    /**
     * 跳转到入住管理页面
     */
    @RequestMapping("live/index")
    public ModelAndView liveIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "live/live/index");
    }

    /**
     * 跳转到退房管理
     */
    @RequestMapping("cancel/index")
    public ModelAndView cancelIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/live/cancel/index");
    }


    /**
     * 订单管理
     */
    @RequestMapping("order/index")
    public ModelAndView orderIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/live/order/index");
    }

    /**
     * 跳转到服务管理
     */
    @RequestMapping("service/index")
    public ModelAndView serviceIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/live/service/index");
    }

}
