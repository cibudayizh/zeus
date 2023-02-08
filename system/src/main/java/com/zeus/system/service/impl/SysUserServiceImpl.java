package com.zeus.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.entity.SysUser;
import com.zeus.system.mapper.SysUserMapper;
import com.zeus.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2023-02-07 16:16:35
*/
@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Long userRegister(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(SecureUtil.sha256(userRegisterDto.getPassword()));
        SysUser sysUser = BeanUtil.copyProperties(userRegisterDto, SysUser.class);
        sysUser.setId(IdUtil.getSnowflakeNextId());
        sysUserMapper.insert(sysUser);
        return sysUser.getId();
    }
}




