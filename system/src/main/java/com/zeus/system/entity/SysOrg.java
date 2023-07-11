package com.zeus.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_org
 */
@TableName(value ="sys_org")
@Data
public class SysOrg implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 组织CODE
     */
    @TableField(value = "org_code")
    private String orgCode;

    /**
     * 机构名称
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 上级code
     */
    @TableField(value = "up_code")
    private String upCode;

    /**
     * 所有上级的code
     */
    @TableField(value = "up_all_code")
    private String upAllCode;

    /**
     * 机构级别
     */
    @TableField(value = "org_level")
    private Integer orgLevel;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联系人
     */
    @TableField(value = "linkman")
    private String linkman;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 删除标志（0代表未删除 1代表删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 创建者
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
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
    private Date updateTime;

    /**
     * 创建组织
     */
    @TableField(value = "create_org")
    private Long createOrg;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 租户编号
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}