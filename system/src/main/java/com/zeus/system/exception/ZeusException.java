package com.zeus.system.exception;

/**
 * 异常
 *
 */
public class ZeusException extends RuntimeException {

    private static final long serialVersionUID = -2663372810959339510L;

    public ZeusException() {
        super();
    }

    public ZeusException(Throwable cause) {
        super(cause);
    }

    public ZeusException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ZeusException(String message) {
        super(message);
    }

    public ZeusException(String message, Throwable cause) {
        super(message, cause);
    }

}
