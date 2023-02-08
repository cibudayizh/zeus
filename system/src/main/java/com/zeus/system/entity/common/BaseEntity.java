package com.zeus.system.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhuzihang
 * @createTime 2023-02-08 008 14:57
 * @description
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

}
