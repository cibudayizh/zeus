package com.zeus.system.exception;

/**
 * DB异常类
 * @author zhuzihang
 * @createTime 2023-02-08 008 11:15
 * @description
 */
public class DBException extends RuntimeException {

    private static final long serialVersionUID = -1286876958585311379L;

    public DBException(String message) {
        super(message);
    }

    public DBException(Throwable throwable) {
        super(throwable);
    }

    public DBException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
