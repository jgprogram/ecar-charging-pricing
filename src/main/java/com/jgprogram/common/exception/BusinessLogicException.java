package com.jgprogram.common.exception;

/**
 *
 * @author jgprogram
 */
public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException() {
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(Throwable cause) {
        super(cause);
    }

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
