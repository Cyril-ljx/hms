package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 员工：记录在案的员工信息，不参与后台管理系统的使用(Staff)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = -36764286162664973L;

    private Integer id;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工性别
     */
    private Integer sex;
    /**
     * 员工生日
     */
    private Object birthday;
    /**
     * 员工岗位ID
     */
    private Integer jobId;
    /**
     * 员工注册时间
     */
    private String createTime;
    /**
     * 员工电话
     */
    private String phone;
    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 所属部门ID
     */
    private Integer depId;

    /**
     * 员工所在部门
     */
    @TableField(exist = false, value = "department")
    private String department;

    /**
     * 员工岗位
     */
    @TableField(exist = false, value = "job")
    private String job;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

}