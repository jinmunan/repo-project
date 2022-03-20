package com.cy.store.service.exception;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 16:15
 */

/**更新异常*/
public class UpdateException extends ServiceException {
    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
