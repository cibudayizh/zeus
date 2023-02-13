package com.zeus.system.annotation;

import java.lang.annotation.*;

/**
 * @author zhuzihang
 * @createTime 2023-02-02 002 14:23
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
//    是否需要校验权限 默认true
    boolean checkPermission() default true;

    //权限编码
    String value() default "";
}

