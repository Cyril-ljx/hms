package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 客户：订房的用户(Client)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Client implements Serializable {
    private static final long serialVersionUID = -63178191311336680L;
    
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
    * 加密盐
    */
    private String salt;
    /**
    * 密码
    */
    private String password;
    /**
    * 电话
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 注册时间
    */
    private String createTime;
    /**
    * 状态
    */
    private Integer state;
    /**
    * 身份证
    */
    private String identify;


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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

}