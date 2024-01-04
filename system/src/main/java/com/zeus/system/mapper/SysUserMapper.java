package com.zeus.system.mapper;

import com.zeus.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeus.system.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-02-07 16:16:54
* @Entity com.zeus.system.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户id查询用户详情信息
     * @param userId 用户id
     * @return
     */
    UserVo getUserInfo(@Param("userId")Long userId);
}




