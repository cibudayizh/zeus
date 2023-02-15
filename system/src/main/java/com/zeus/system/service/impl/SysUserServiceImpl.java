package com.zeus.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeus.system.dto.UserLoginDto;
import com.zeus.system.dto.UserRegisterDto;
import com.zeus.system.entity.SysUser;
import com.zeus.system.exception.BusinessException;
import com.zeus.system.mapper.SysUserMapper;
import com.zeus.system.service.SysUserService;
import com.zeus.system.utils.RedisUtil;
import com.zeus.system.utils.ZeusJwtUtil;
import com.zeus.system.vo.common.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.zeus.system.constants.RedisPrefixConstant.USER_TOKEN_PREFIX;
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

    @Autowired
    private ZeusJwtUtil zeusJwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 过期时间 xx毫秒
     */
    @Value("${token.expired}")
    private Integer timeOut;

    @Override
    public Long userRegister(UserRegisterDto userRegisterDto) {
        //检查account账号是否存在重复
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",userRegisterDto.getAccount()).eq(DEL_FLAG,0);
        if(sysUserMapper.selectCount(queryWrapper)>0){
            throw new BusinessException(ResultVO.EXIST.getCode(),"该账号已存在");
        }
        userRegisterDto.setPassword(SecureUtil.sha256(userRegisterDto.getPassword()));
        SysUser sysUser = BeanUtil.copyProperties(userRegisterDto, SysUser.class);
        sysUser.setId(IdUtil.getSnowflakeNextId());
        sysUser.setStatus(1);
        sysUserMapper.insert(sysUser);
        return sysUser.getId();
    }

    @Override
    public String userLogin(UserLoginDto userLoginDto) {
        //判断用户名密码是否正确
        QueryWrapper<SysUser> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("account",userLoginDto.getAccount()).eq(DEL_FLAG,0);
        SysUser sysUser;
        try{
            sysUser= sysUserMapper.selectOne(userQueryWrapper);
        }catch (Exception e){
            throw new BusinessException(ResultVO.EXIST.getCode(),"该存在多个相同账号，请联系管理员");
        }
        //校验密码
        if(!sysUser.getPassword().equals(SecureUtil.sha256(userLoginDto.getPassword()))){
            throw new BusinessException(ResultVO.FAIL.getCode(),"用户名密码错误");
        }
        if(sysUser.getStatus()==1){
            throw new BusinessException(ResultVO.FAIL.getCode(),"账号已经停用，请联系管理员启用账号");
        }
        //生成token
        String token = zeusJwtUtil.sign(sysUser.getId(), sysUser.getTenantId());
        String userTokenId = USER_TOKEN_PREFIX.concat(String.valueOf(sysUser.getId()));
        redisUtil.setCacheObject(userTokenId,token,timeOut, TimeUnit.MILLISECONDS);
        return token;
    }
}




