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
@ApiModel(value = "注册用户参数")
public class UserRegisterDto {
    @ApiModelProperty(value = "账号" ,required = true)
    private String account;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @ApiModelProperty(value = "手机号",required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像图片地址")
    private String avatar;

}
