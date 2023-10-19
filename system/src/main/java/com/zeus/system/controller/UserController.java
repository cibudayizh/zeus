package com.zeus.system.controller;

import com.zeus.system.service.UserService;
import com.zeus.system.vo.UserVo;
import com.zeus.system.vo.common.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuzihang
 * @createTime 2023-07-10 010 16:25
 * @description
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关api")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/getUserInfo")
    public ResultVO<UserVo> getUserInfo(@RequestParam("userId")Long userId){
        return new ResultVO<>(userService.getUser(userId));
    }

}
