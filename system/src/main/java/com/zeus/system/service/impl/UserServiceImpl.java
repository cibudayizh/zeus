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
import com.zeus.system.service.UserService;
import com.zeus.system.utils.RedisUtil;
import com.zeus.system.utils.ZeusJwtUtil;
import com.zeus.system.vo.UserVo;
import com.zeus.system.vo.common.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.zeus.system.constants.RedisPrefixConstant.*;
import static com.zeus.system.constants.TableFiledConstant.DEL_FLAG;

/**
 * @author Administrator
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
 * @createDate 2023-02-07 16:16:35
 */
@Service
public class UserServiceImpl implements UserService {

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
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", userRegisterDto.getAccount()).eq(DEL_FLAG, 0);
        if (sysUserMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException(ResultVO.EXIST.getCode(), "该账号已存在");
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
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", userLoginDto.getAccount()).eq(DEL_FLAG, 0);
        //从数据库层面禁止相同账号出现
        SysUser sysUser = sysUserMapper.selectOne(userQueryWrapper);
        if(sysUser==null){
            throw new BusinessException(ResultVO.ARGUMENT_ILLEGAL.getCode(), "用户名密码错误");
        }
        if (sysUser.getStatus() == 1) {
            throw new BusinessException(ResultVO.FORBIDDEN.getCode(), "账号已经停用，请联系管理员启用账号");
        }
        String pwdErrorKey = PWD_ERR_CNT_KEY.concat(String.valueOf(sysUser.getId()));
        Object cacheObject = redisUtil.getCacheObject(pwdErrorKey);
        //校验密码
        if (!sysUser.getPassword().equals(SecureUtil.sha256(userLoginDto.getPassword()))) {


            throw new BusinessException(ResultVO.ARGUMENT_ILLEGAL.getCode(), "用户名密码错误");
        }

        //生成token
        String token = zeusJwtUtil.sign(sysUser.getId(), sysUser.getTenantId());
        String userTokenKey = USER_TOKEN_PREFIX.concat(String.valueOf(sysUser.getId()));
        redisUtil.setCacheObject(userTokenKey, token, timeOut, TimeUnit.MILLISECONDS);
        return token;
    }

    @Override
    public UserVo getUser(Long userId) {
        UserVo userVo = redisUtil.getCacheObject(USER_INFORMATION_PREFIX + userId);
        if (userVo!=null){
            return userVo;
        }
        return null;
    }
}




