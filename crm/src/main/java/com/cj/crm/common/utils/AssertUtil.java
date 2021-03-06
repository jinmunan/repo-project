package com.cj.crm.common.utils;


import com.cj.crm.common.exceptions.ParamsException;

public class AssertUtil {

    /**
     * 如果是真则抛出参数异常
     * @param flag
     * @param msg
     */
    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }
}
