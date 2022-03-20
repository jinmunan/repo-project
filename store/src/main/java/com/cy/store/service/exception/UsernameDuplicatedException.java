package com.cy.store.service.exception;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 16:15
 */

/**用户名被占用*/
public class UsernameDuplicatedException extends ServiceException {
    public UsernameDuplicatedException() {
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    public UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
