package com.zeus.system.annotation;

import java.lang.annotation.*;

/**
 * Controller默认进行ResultVO包装，对于特殊不需要的，使用该注解可以忽略包装
 * @author zhuzihang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultVO_Ignore {

}
