package com.jgprogram.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jgprogram
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation error")
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
