package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2021-02-18 12:45:40
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 607963444638620181L;
    
    private Integer roleId;
    
    private String permission;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}