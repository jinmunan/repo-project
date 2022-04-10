package com.xxxx.crm.enums;

/**
 *
 * 客户服务状态枚举类
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public enum  CustomerServeStatus {
    // 创建
    CREATED("fw_001"),
    // 分配
    ASSIGNED("fw_002"),
    // 处理
    PROCED("fw_003"),
    // 反馈
    FEED_BACK("fw_004"),
    // 归档
    ARCHIVED("fw_005");

    private String state;

    CustomerServeStatus(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
