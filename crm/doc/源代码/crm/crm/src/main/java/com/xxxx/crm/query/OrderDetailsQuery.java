package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class OrderDetailsQuery extends BaseQuery {

    private Integer orderId; // 订单ID

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
