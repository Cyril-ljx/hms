package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 房间：可入住的房间(Room)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Room implements Serializable {
    private static final long serialVersionUID = 783476945263942448L;
    /**
     *
     */
    private Integer id;
    /**
     * 房间编号
     */
    private String roomNum;
    /**
     * 状态：0 空闲 1 预定中 2 入住中 3 退房待清理
     */
    private Integer state;

    private Integer useable;
    /**
     * 类型ID
     */
    private Integer typeId;

    @TableField(exist = false, value = "price")
    private BigDecimal price;

    @TableField(exist = false, value = "imgs")
    private String imgs;

    @TableField(exist = false, value = "title")
    private String type;

    @TableField(exist = false, value = "room_info")
    private String roomInfo;

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUseable() {
        return useable;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}