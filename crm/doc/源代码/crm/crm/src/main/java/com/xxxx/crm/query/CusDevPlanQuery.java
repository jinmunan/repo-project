package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class CusDevPlanQuery extends BaseQuery {

    private Integer saleChanceId; // 营销机会的主键

    public Integer getSaleChanceId() {
        return saleChanceId;
    }

    public void setSaleChanceId(Integer saleChanceId) {
        this.saleChanceId = saleChanceId;
    }
}
