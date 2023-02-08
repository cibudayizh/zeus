package com.zeus.system.exception;


import com.zeus.system.vo.common.ResultVO;

/**
 * 业务异常
 */
public class BusinessException extends ZeusException {

    private static final long serialVersionUID = 6263350944702493818L;

    private final String code;
    private final String message;

    public BusinessException(Throwable cause) {
        super(cause);
        this.code = ResultVO.EXCEPTION.getCode();
        this.message = cause.getMessage();
    }

    public BusinessException(String code, String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
