package com.zeus.system.constants;

/**
 * @author zhuzihang
 * @createTime 2023-02-09 009 15:08
 * @description
 */
public class RedisPrefixConstant {
        /**
         * user_token_userId
         */
        public final static String USER_TOKEN_PREFIX="login_tokens:";

        /**
         * 防重提交 redis key
         */
        public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";


        /**
         * 登录账户密码错误次数 redis key
         */
        public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
