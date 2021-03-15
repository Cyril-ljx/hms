package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 酒店信息模板(InfoModel)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class InfoModel implements Serializable {
    private static final long serialVersionUID = 133710067836988557L;
    /**
     *
     */
    private Integer id;
    /**
     * 模板标题
     */
    private String hTitle;
    /**
     * 酒店名
     */
    private String hName;
    /**
     * 酒店地址
     */
    private String hAddress;
    /**
     * 酒店电话
     */
    private String hPhone;
    /**
     * 酒店负责人
     */
    private String hManager;
    /**
     * 酒店负责人电话
     */
    private String hMPhone;
    /**
     * 酒店介绍
     */
    private String hInfo;
    /**
     * 主页轮播图
     */
    private String hImgs;

    private Integer isUse;

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHTitle() {
        return hTitle;
    }

    public void setHTitle(String hTitle) {
        this.hTitle = hTitle;
    }

    public String getHName() {
        return hName;
    }

    public void setHName(String hName) {
        this.hName = hName;
    }

    public String getHAddress() {
        return hAddress;
    }

    public void setHAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String getHPhone() {
        return hPhone;
    }

    public void setHPhone(String hPhone) {
        this.hPhone = hPhone;
    }

    public String getHManager() {
        return hManager;
    }

    public void setHManager(String hManager) {
        this.hManager = hManager;
    }

    public String getHMPhone() {
        return hMPhone;
    }

    public void setHMPhone(String hMPhone) {
        this.hMPhone = hMPhone;
    }

    public String getHInfo() {
        return hInfo;
    }

    public void setHInfo(String hInfo) {
        this.hInfo = hInfo;
    }

    public String getHImgs() {
        return hImgs;
    }

    public void setHImgs(String hImgs) {
        this.hImgs = hImgs;
    }

}