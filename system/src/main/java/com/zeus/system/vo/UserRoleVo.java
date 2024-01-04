package com.zeus.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhuzihang
 * @createTime 2023-10-19 019 15:26
 * @description
 */
@Data
@ApiModel(value = "用户角色信息")
public class UserRoleVo {
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
