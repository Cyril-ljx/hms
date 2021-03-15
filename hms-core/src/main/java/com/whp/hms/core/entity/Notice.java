package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 前台公告栏(Notice)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 500981908625082448L;
    
    private Integer id;
    /**
    * 标题
    */
    private String title;
    /**
    * 内容
    */
    private String content;
    /**
    * 发布时间
    */
    private String createTime;
    /**
    * 只能有一个显示在前台，其余在公告列表里
    */
    private Integer isShow;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

}