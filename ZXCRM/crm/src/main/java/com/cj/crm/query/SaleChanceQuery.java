package com.cj.crm.query;

import com.cj.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 16:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleChanceQuery extends BaseQuery {
    private String customerName; //客户名
    private String createMan; //创建人
    private Integer state; //分配状态
}
