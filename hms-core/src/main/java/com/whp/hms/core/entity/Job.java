package com.whp.hms.core.entity;

import java.io.Serializable;

/**
 * 岗位、职务(Job)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Job implements Serializable {
    private static final long serialVersionUID = -61639765076286349L;
    
    private Integer id;
    /**
    * 岗位
    */
    private String job;
    /**
    * 岗位职能
    */
    private String description;
    /**
    * 岗位人数限制
    */
    private Integer count;
    /**
    * 工资/月
    */
    private Double wages;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

}