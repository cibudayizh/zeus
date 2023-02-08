package com.zeus.system.service;

import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zeus.system.vo.UserVo;

/**
* @author Administrator
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2023-02-07 16:16:54
*/
public interface SysUserService {

    /**
     * 注册用户
     * @param userRegisterDto
     * @return
     */
    Long userRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户登录校验
     * @param userLoginDto
     * @return
     */
    String userLogin(UserLoginDto userLoginDto);
}
