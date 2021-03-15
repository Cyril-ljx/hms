package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 订房记录(ReserveRecord)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class ReserveRecord implements Serializable {
    private static final long serialVersionUID = -50961557878061621L;

    private Integer id;
    /**
     * 预定房间号
     */
    private String roomNum;
    /**
     * 生成时间
     */
    private String createTime;
    /**
     * 0 取消所生成 1 入住所生成
     */
    private Integer flag;

    @TableField(exist = false, value = "nickname")
    private String nickname;


    private Integer clientId;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}