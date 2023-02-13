package com.zeus.system.constants;



/**
 * @author zhuzihang
 * @createTime 2023-02-13 013 15:17
 * @description
 */
public class PermissionConstant {
    /**
     * 不需要登录即可访问的权限代码
     */
    public static final String ANONYMOUS = "anonymous";
    /**
     * 登录后不需要分配角色就可以访问的权限代码
      */
    public static final String LOGIN = "login";

    /**
     *  未加permission的会自动添加anonymous权限编码
     */
    public static final String PERMISSION_LEVEL_LOW="low";

    /**
     *  high 未加permission的会自动添加login权限编码
     */
    public static final String PERMISSION_LEVEL_HIGH="high";
}
