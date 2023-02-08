package com.zeus.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhuzihang
 * @createTime 2023-02-07 007 17:00
 * @description
 */
@Data
@ApiModel(value = "登录请求对象")
public class UserLoginDto {
    @ApiModelProperty(value = "账号" ,required = true)
    private String account;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
