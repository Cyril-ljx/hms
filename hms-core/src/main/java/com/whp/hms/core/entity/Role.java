package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 角色：后台管理员的权限角色(Role)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 702333377938681320L;
    
    private Integer id;
    /**
    * 角色
    */
    private String role;
    /**
    * 角色等级
    */
    private String level;
    /**
    * 角色职能
    */
    private String detail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}