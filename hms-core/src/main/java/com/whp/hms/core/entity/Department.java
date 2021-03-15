package com.whp.hms.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * 酒店组织架构(Department)实体类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public class Department implements Serializable {
    private static final long serialVersionUID = -43902058308131271L;

    private Integer id;
    /**
     * 上级部门
     */
    private Integer hId;
    /**
     * 部门名
     */
    private String depName;
    /**
     * 部门备注
     */
    private String description;
    /**
     * 排序
     */
    private Integer depOrder;

    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHId() {
        return hId;
    }

    public void setHId(Integer hId) {
        this.hId = hId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepOrder() {
        return depOrder;
    }

    public void setDepOrder(Integer depOrder) {
        this.depOrder = depOrder;
    }

}