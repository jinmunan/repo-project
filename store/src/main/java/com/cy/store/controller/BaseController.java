package com.cy.store.controller;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 17:24
 */

import com.cy.common.utils.JsonResult;
import com.cy.store.service.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.print.attribute.standard.Finishings;
import javax.servlet.http.HttpSession;
import java.io.DataInput;

/**
 * 控制层的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    /*请求处理方法,这个方法的返回值就是需要传递给前端的数据*/
    /*自动将异常对象传递给此方法的参数列表上*/
    /*当项目中产生了异常,被统一拦截到此方法,此方法就是充当处理请求的方法 方法的返回值给到前端*/
    @ExceptionHandler({ServiceException.class, FileUploadException.class}) //用于同意处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被注册");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码不匹配");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空异常");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件尺寸异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型异常");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态异常");
        } else if (e instanceof FileUploadException) {
            result.setState(6004);
            result.setMessage("文件上传异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户的收获地址超出上限的异常");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("用户的收获地址不存在的异常");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("用户的收获地址访问的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
