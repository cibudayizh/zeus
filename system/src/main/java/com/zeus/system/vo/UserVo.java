package com.zeus.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zhuzihang
 * @createTime 2023-02-07 007 17:05
 * @description
 */
@Data
@ApiModel(value = "用户详细信息")
public class UserVo {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long id;

    /**
     * 所属部门ID
     */
    @ApiModelProperty(value = "所属部门ID")
    private Long orgId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String orgName;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String account;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String name;

    /**
     * 用户类型（00系统用户）
     */
    @ApiModelProperty(value = "用户类型")
    private String type;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码 国内11位
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像图片地址")
    private String avatar;


    /**
     * 账号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "账号状态")
    private Integer status;

    /**
     * 最后登录IP
     */
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;

    /**
     * 用户token
     */
    @ApiModelProperty(value = "用户token")
    private String token;
}
