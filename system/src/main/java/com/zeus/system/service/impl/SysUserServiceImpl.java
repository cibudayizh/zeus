package com.zeus.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.entity.SysUser;
import com.zeus.system.exception.BusinessException;
import com.zeus.system.mapper.SysUserMapper;
import com.zeus.system.service.SysUserService;
import com.zeus.system.vo.common.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zeus.system.constants.TableFiledConstant.DEL_FLAG;

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
        //检查account账号是否存在重复
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",userRegisterDto.getAccount());
        if(sysUserMapper.selectCount(queryWrapper)>0){
            throw new BusinessException(ResultVO.EXIST.getCode(),"该账号已存在");
        }
        userRegisterDto.setPassword(SecureUtil.sha256(userRegisterDto.getPassword()));
        SysUser sysUser = BeanUtil.copyProperties(userRegisterDto, SysUser.class);
        sysUser.setId(IdUtil.getSnowflakeNextId());
        sysUserMapper.insert(sysUser);
        return sysUser.getId();
    }

    @Override
    public String userLogin(UserLoginDto userLoginDto) {

        return null;
    }
}




