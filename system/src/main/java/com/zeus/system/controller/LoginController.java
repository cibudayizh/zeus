package com.zeus.system.controller;

import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.service.SysUserService;
import com.zeus.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuzihang
 * @createTime 2023-02-07 007 16:20
 * @description
 */
@RestController
@RequestMapping("/login/v1")
@Api(tags = "登录验证api")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public UserVo login(UserLoginDto userLoginDto) {
        return null;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Long register(UserRegisterDto userRegisterDto) {
        return sysUserService.userRegister(userRegisterDto);
    }
}
