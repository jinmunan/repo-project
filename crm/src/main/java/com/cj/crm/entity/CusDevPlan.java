package com.cj.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 客户开发计划
 * @TableName t_cus_dev_plan
 */
@TableName(value = "t_cus_dev_plan")
@Data
public class CusDevPlan implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer saleChanceId;

    /**
     *
     */
    private String planItem;

    /**
     *
     */
    private Date planDate;

    /**
     *
     */
    private String exeAffect;

    /**
     * 格式化时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 格式化时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    /**
     *
     */
    private Integer isValid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}