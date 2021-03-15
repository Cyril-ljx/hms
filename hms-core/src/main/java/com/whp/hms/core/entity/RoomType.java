package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 房间类型(RoomType)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class RoomType implements Serializable {
    private static final long serialVersionUID = 776785457393249707L;
    
    private Integer id;
    /**
    * 房子类型
    */
    private String title;
    /**
    * 类型介绍
    */
    private String roomInfo;
    /**
    * 是否配置wifi
    */
    private Integer isWifi;
    /**
    * 是否配置空调
    */
    private Integer isAir;
    /**
    * 是否配置热水
    */
    private Integer isHot;
    /**
    * 是否配置卫生间
    */
    private Integer isWc;
    /**
    * 是否配置暖气
    */
    private Integer isHotAir;
    /**
    * 是否配置饮水机
    */
    private Integer isWater;
    /**
    * 房间图片
    */
    private String imgs;
    /**
    * 价格 ￥/日 不满一天按照一天算
    */
    private Double price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public Integer getIsWifi() {
        return isWifi;
    }

    public void setIsWifi(Integer isWifi) {
        this.isWifi = isWifi;
    }

    public Integer getIsAir() {
        return isAir;
    }

    public void setIsAir(Integer isAir) {
        this.isAir = isAir;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsWc() {
        return isWc;
    }

    public void setIsWc(Integer isWc) {
        this.isWc = isWc;
    }

    public Integer getIsHotAir() {
        return isHotAir;
    }

    public void setIsHotAir(Integer isHotAir) {
        this.isHotAir = isHotAir;
    }

    public Integer getIsWater() {
        return isWater;
    }

    public void setIsWater(Integer isWater) {
        this.isWater = isWater;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}