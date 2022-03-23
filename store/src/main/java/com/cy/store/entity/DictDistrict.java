package com.cy.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @TableName t_dict_district
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="t_dict_district")
@Data
public class DictDistrict extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String parent;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}