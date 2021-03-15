package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 图片上传记录(ImgLog)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class ImgLog implements Serializable {
    private static final long serialVersionUID = 805812992381290302L;

    private Integer id;

    private String imgUrl;

    private Integer uId;

    private String createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ImgLog(String imgUrl, Integer uId, String createDate) {
        this.imgUrl = imgUrl;
        this.uId = uId;
        this.createDate = createDate;
    }

    public ImgLog(Integer id, String imgUrl, Integer uId, String createDate) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.uId = uId;
        this.createDate = createDate;
    }
    public ImgLog(){

    }
}