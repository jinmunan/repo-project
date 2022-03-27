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
 * @TableName t_product_category
 */
@TableName(value ="t_product_category")
@Data
public class ProductCategory implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态   1：正常   0：删除
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 是否是父分类   1：是  0：否
     */
    private Integer isParent;

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