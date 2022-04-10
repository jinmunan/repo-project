package com.xxxx.crm.model;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class TreeModel {

    private Integer id;
    private Integer pId;
    private String name;
    private boolean checked = false; // 复选框是否被勾选。如果是true，则勾选；如果是false，则不勾选

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
