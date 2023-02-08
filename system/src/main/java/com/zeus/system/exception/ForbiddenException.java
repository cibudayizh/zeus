package com.zeus.system.exception;

/**
 * 没有权限异常，一般发生在Token无效场景
 * @author zhuzihang
 * @createTime 2023-02-08 008 11:18
 * @description
 */
public class ForbiddenException extends ZeusException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

}