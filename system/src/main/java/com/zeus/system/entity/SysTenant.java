package com.zeus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 租户表
 * @TableName sys_tenant
 */
@TableName(value ="sys_tenant")
@Data
public class SysTenant implements Serializable {
    /**
     * 租户编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户名
     */
    private String name;

    /**
     * 联系人的用户id
     */
    private Long contact_user_id;

    /**
     * 联系人
     */
    private String contact_name;

    /**
     * 联系手机
     */
    private String contact_mobile;

    /**
     * 租户状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 绑定域名
     */
    private String domain;

    /**
     * 租户套餐编号
     */
    private Long package_id;

    /**
     * 过期时间
     */
    private Date expire_time;

    /**
     * 账号数量
     */
    private Integer account_count;

    /**
     * 创建者
     */
    private Long create_user;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新者
     */
    private Long update_user;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否删除
     */
    private Integer del_flag;

    /**
     * 创建组织
     */
    private Long create_org;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}