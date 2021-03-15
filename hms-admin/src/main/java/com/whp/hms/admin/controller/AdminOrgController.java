package com.whp.hms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.hms.admin.service.DepartmentService;
import com.whp.hms.admin.service.JobService;
import com.whp.hms.admin.service.StaffService;
import com.whp.hms.core.entity.Department;
import com.whp.hms.core.entity.Job;
import com.whp.hms.core.entity.Notice;
import com.whp.hms.core.entity.Staff;
import com.whp.hms.core.util.DateUtil;
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
import java.util.Stack;

@Controller
@RequestMapping("/hms/admin/org")
public class AdminOrgController {
    private static final Logger logger = LoggerFactory.getLogger(AdminOrgController.class);

    @Resource
    private DepartmentService departmentService;

    @Resource
    private JobService jobService;

    @Resource
    private StaffService staffService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/department/list")
    public LayuiVO departmentList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有部门信息-------------");
            List<Department> clients = departmentService.queryAllByLimit((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(departmentService.queryCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有部门失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取所有部门
     */
    @ResponseBody
    @RequestMapping("allDepartment")
    public Object allDepartment() {
        try {
            logger.info("-------------获取所有部门信息-------------");
            return Response.success(departmentService.queryAll());
        } catch (Exception e) {
            logger.error("获取所有部门信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 增加部门
     */
    @ResponseBody
    @RequestMapping("/department/insert")
    public Object insertDep(@RequestBody Department department) {
        try {
            logger.info("-------------添加部门-------------");
            Department result = departmentService.insert(department);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加部门失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改部门
     */
    @ResponseBody
    @RequestMapping("/department/update")
    public Object update(@RequestBody Department department) {
        try {
            logger.info("-------------修改部门-------------");
            Department result = departmentService.update(department);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改部门失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除部门
     */
    @ResponseBody
    @RequestMapping("/department/del")
    public Object del(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除部门-------------");
            Integer count = staffService.queryByDepId(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (count != 0) {
                return Response.error("该部门人数不为0不能删除！");
            }
            return Response.success(departmentService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除部门失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/staff/list")
    public LayuiVO staffList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有员工信息-------------");
            List<Staff> clients = staffService.queryStaffList((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(staffService.queryCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有员工失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 增加员工
     */
    @ResponseBody
    @RequestMapping("/staff/insert")
    public Object insertStaff(@RequestBody Staff staff) {
        try {
            logger.info("-------------添加员工-------------");
            staff.setCreateTime(DateUtil.getNowTime());
            Staff result = staffService.insert(staff);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加员工失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改员工
     */
    @ResponseBody
    @RequestMapping("/staff/update")
    public Object updateStaff(@RequestBody Staff staff) {
        try {
            logger.info("-------------修改员工-------------");
            staff.setCreateTime(DateUtil.getNowTime());
            Staff result = staffService.update(staff);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改员工失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除员工
     */
    @ResponseBody
    @RequestMapping("/staff/del")
    public Object delStaff(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除员工-------------");
            return Response.success(staffService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除员工失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/job/list")
    public LayuiVO jobList(Integer page, Integer limit) {
        try {
            logger.info("-------------查询所有岗位信息-------------");
            List<Job> clients = jobService.queryAllByLimit((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(jobService.queryCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            logger.info("查询所有岗位失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取所有岗位
     */
    @ResponseBody
    @RequestMapping("allJob")
    public Object allJob() {
        try {
            logger.info("-------------获取所有岗位信息-------------");
            return Response.success(jobService.queryAll());
        } catch (Exception e) {
            logger.error("获取所有岗位信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 增加岗位
     */
    @ResponseBody
    @RequestMapping("/job/insert")
    public Object insertJob(@RequestBody Job job) {
        try {
            logger.info("-------------添加岗位-------------");
            Job result = jobService.insert(job);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("添加失败");
        } catch (Exception e) {
            logger.error("添加岗位失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除岗位
     */
    @ResponseBody
    @RequestMapping("/job/del")
    public Object delJob(@RequestBody String jsonBody) {
        try {
            logger.info("-------------删除岗位-------------");
            Integer count = staffService.queryByJobId(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (count != 0) {
                return Response.error("该岗位人数不为0不能删除！");
            }
            return Response.success(jobService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            logger.error("删除岗位失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改岗位
     */
    @ResponseBody
    @RequestMapping("/job/update")
    public Object updateJob(@RequestBody Job job) {
        try {
            logger.info("-------------修改岗位-------------");
            Job result = jobService.update(job);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            logger.error("修改岗位失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


    /**
     * 部门详情
     */
    @ResponseBody
    @RequestMapping("department/detail")
    public Object departmentDetail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------部门详情-------------");
            Department department = departmentService.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (department != null) {
                return Response.success(department);
            }
            return Response.error("获取失败");
        } catch (Exception e) {
            logger.error("部门详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 员工详情
     */
    @ResponseBody
    @RequestMapping("staff/detail")
    public Object staffDetail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------员工详情-------------");
            Staff staff = staffService.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (staff != null) {
                return Response.success(staff);
            }
            return Response.error("获取失败");
        } catch (Exception e) {
            logger.error("员工详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 岗位详情
     */
    @ResponseBody
    @RequestMapping("job/detail")
    public Object jobDetail(@RequestBody String jsonBody) {
        try {
            logger.info("-------------岗位详情-------------");
            Job job = jobService.queryById(JSONObject.parseObject(jsonBody).getInteger("id"));
            if (job != null) {
                return Response.success(job);
            }
            return Response.error("获取失败");
        } catch (Exception e) {
            logger.error("岗位详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
