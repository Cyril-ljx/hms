package com.whp.hms.admin.utils;

import com.whp.hms.core.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class LayTree {
    private Integer id;
    private String title;
    private List<LayTree> children;

    public LayTree() {

    }


    public LayTree(Department department) {
        this.id = department.getId();
        this.title = department.getDepName();
        children = new ArrayList<>();
    }

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

    public List<LayTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayTree> children) {
        this.children = children;
    }
}
