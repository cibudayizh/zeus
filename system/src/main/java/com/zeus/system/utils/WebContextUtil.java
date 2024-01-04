package com.zeus.system.utils;



import com.zeus.system.vo.UserVo;

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
    private static ThreadLocal<UserVo> local = new ThreadLocal<>();
    /**
     * 设置user信息
     * @param userVo
     */
    public static void setUserInfo(UserVo userVo){
        removeUserInfo();
        local.set(userVo);
    }
    /**
     * 获取user信息
     * @return
     */
    public static UserVo getUserInfo(){
        if(local.get() != null){
            UserVo userVo = local.get();
            return userVo;
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