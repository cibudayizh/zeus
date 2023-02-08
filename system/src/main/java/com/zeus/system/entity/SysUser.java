package com.zeus.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.zeus.system.entity.common.BaseEntity;
import lombok.Data;

/**
 * 用户信息表
 * @author Administrator
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 所属部门ID
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 用户账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 用户名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户类型（00系统用户）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码 国内11位
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private Date loginDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}