package com.cy.store.service.exception;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 10:18
 */

/**
 * 收获地址大于20条的异常
 */
public class AddressCountLimitException extends ServiceException {
    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
