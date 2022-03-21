package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String truename;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private Integer isvalid;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}