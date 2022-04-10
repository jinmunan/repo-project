package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class CustomerOrderQuery extends BaseQuery {

    private Integer cusId;  // 客户ID

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
