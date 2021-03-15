package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 入住记录(LiveRecord)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class LiveRecord implements Serializable {
    private static final long serialVersionUID = 607671062451239633L;

    private Integer id;
    /**
     * 入住房间号
     */
    private String roomNum;
    /**
     * 入住时间
     */
    private String liveTime;
    /**
     * 退房时间
     */
    private String endTime;
    /**
     * 总计花费
     */
    private BigDecimal totalPrice;

    private Integer clientId;


    @TableField(exist = false, value = "nickname")
    private String nickname;

    @TableField(exist = false, value = "title")
    private String title;

    @TableField(exist = false, value = "imgs")
    private String imgs;

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}