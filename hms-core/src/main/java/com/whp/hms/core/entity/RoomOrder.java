package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 订房订单：客户预定房间的记录(RoomOrder)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class RoomOrder implements Serializable {
    private static final long serialVersionUID = -68916862328980750L;

    private Integer id;
    /**
     * 房间ID
     */
    private Integer roomId;
    /**
     * 订房用户
     */
    private Integer uId;
    /**
     * 入住时间
     */
    private String liveTime;
    /**
     * 入住天数 | 可续
     */
    private Integer liveDay;
    /**
     * 订单状态：0 预定 1 入住 2 已退房 3 取消预定
     */
    private Integer state;


    /**
     * 客户姓名
     */
    @TableField(exist = false, value = "nickname")
    private String nickname;

    /**
     * 房间类型
     */
    @TableField(exist = false, value = "title")
    private String roomType;

    /**
     * 房间编号
     */
    @TableField(exist = false, value = "room_num")
    private String roomNum;

    @TableField(exist = false, value = "imgs")
    private String imgs;

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public Integer getLiveDay() {
        return liveDay;
    }

    public void setLiveDay(Integer liveDay) {
        this.liveDay = liveDay;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}