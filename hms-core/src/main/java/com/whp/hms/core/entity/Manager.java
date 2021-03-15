package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员：可以登录后台管理的用户(Manager)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Manager implements Serializable {
    private static final long serialVersionUID = 629225321445708396L;

    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 加密盐
     */
    private String salt;
    /**
     * 职务ID
     */
    private Integer jobId;

    private String roleName;

    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }


}