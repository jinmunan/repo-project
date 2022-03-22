package com.cj.crm.enums;

/**
 * Created by Jinmunan
 * 2022/3/22
 * 11:34
 */
public enum StateStatus {
    //未分配
    UNSTATE(0),
    //已分配
    STATED(1);

    private Integer type;

    StateStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
