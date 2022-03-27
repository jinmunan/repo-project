package com.cy.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_product
 */
@TableName(value ="t_product")
@Data
public class Product implements Serializable {
    /**
     * 商品id
     */
    @TableId
    private Integer id;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 商品系列
     */
    private String itemType;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品单价
     */
    private Long price;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 商品状态  1：上架   2：下架   3：删除
     */
    private Integer status;

    /**
     * 显示优先级
     */
    private Integer priority;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 最后修改人
     */
    private String modifiedUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}