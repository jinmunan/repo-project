package com.xxxx.crm.annoation;

import java.lang.annotation.*;

/**
 * 定义方法需要的对应资源的权限码
 *
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermission {
    // 权限码
    String code() default "";
}
