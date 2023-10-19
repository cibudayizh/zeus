package com.zeus.system.controller;

import com.zeus.system.annotation.Permission;
import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.service.UserService;
import com.zeus.system.utils.RedisUtil;
import com.zeus.system.vo.common.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuzihang
 * @createTime 2023-02-07 007 16:20
 * @description
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录验证api")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/v1/login")
    @Permission(checkPermission = false)
    public ResultVO<String> login(UserLoginDto userLoginDto) {
        return new ResultVO<>(userService.userLogin(userLoginDto));
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/v1/register")
    @Permission(checkPermission = false)
    public ResultVO<Long> register(UserRegisterDto userRegisterDto) {
        return new ResultVO<>(userService.userRegister(userRegisterDto));
    }

    @GetMapping("/redis/{key}/{value}")
    public ResultVO<Boolean> redisTest(@PathVariable String key, @PathVariable String value) {
        redisUtil.setCacheObject(key, value);
        return new ResultVO<>(redisUtil.hasKey(key));
    }
}
