package com.zeus.system.utils;


import com.zeus.system.entity.SysUser;

/**
 * @Author: zhangliang
 * @CreateTime: 2023-1-3  15:22
 * @Description: TODO
 * @Version: 1.0
 */
public class WebContextUtil {
    /**
     * 本地线程缓存用户信息名称
     */
    private static ThreadLocal<SysUser> local = new ThreadLocal<>();
    /**
     * 设置user信息
     * @param user
     */
    public static void setUserInfo(SysUser user){
        removeUserInfo();
        local.set(user);
    }
    /**
     * 获取user信息
     * @return
     */
    public static SysUser getUserInfo(){
        if(local.get() != null){
            SysUser user = local.get();
            return user;
        }
        return null;
    }
    /**
     * 移除user信息
     * @return
     */
    public static void removeUserInfo(){
        if(local.get() != null){
            local.remove();
        }
    }
}