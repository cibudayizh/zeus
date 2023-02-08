package com.zeus.system.controller;

import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.service.SysUserService;
import com.zeus.system.utils.RedisUtil;
import com.zeus.system.vo.UserVo;
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
@RequestMapping("/login/v1")
@Api(tags = "登录验证api")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResultVO<String> login(UserLoginDto userLoginDto) {
        return null;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ResultVO<Long> register(UserRegisterDto userRegisterDto) {
        return  new ResultVO<>(sysUserService.userRegister(userRegisterDto));
    }

    @GetMapping("/redis/{key}/{value}")
    public ResultVO<Boolean> redisTest(@PathVariable String key,@PathVariable String value){
        redisUtil.delete(key);
        return new ResultVO<>(redisUtil.hasKey(key));
    }
}
