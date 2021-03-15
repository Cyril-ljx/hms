package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 临时服务：用户通过 前台预定系统 发送的服务请求(TempService)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:33
 */
public class TempService implements Serializable {
    private static final long serialVersionUID = -56600972396805507L;

    private Integer id;
    /**
     * 房间号码
     */
    private String roomNum;
    /**
     * 问题描述
     */
    private String content;
    /**
     * 要求
     */
    private String serviceRequire;
    /**
     * 生成时间
     */
    private String createTime;
    /**
     * 状态 0 未完成 1已完成
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServiceRequire() {
        return serviceRequire;
    }

    public void setServiceRequire(String serviceRequire) {
        this.serviceRequire = serviceRequire;
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

}