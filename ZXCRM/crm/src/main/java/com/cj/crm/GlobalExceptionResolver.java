package com.cj.crm;

import com.alibaba.fastjson.JSON;
import com.cj.common.base.ResultInfo;
import com.cj.common.exceptions.NoLoginException;
import com.cj.common.exceptions.ParamsException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.logging.Handler;

/**
 * 全局异常的处理
 * Created by Jinmunan
 * 2022/3/21
 * 15:02
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /**
         * 方法返回值异常 视图和json
         * 约定:如果方法级别配置@ResponseBody 返回json
         */
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("code", 500);
        mv.addObject("msg", "系统异常，请重试...");

        //未登录异常 优先级最高
        if (ex instanceof NoLoginException) {
            mv.setViewName("no_login");
            mv.addObject("msg", "用户未登录!");
            mv.addObject("ctx", request.getContextPath());
            return mv;
        }

        if (handler instanceof HandlerMethod) {
            //反射
            HandlerMethod hm = (HandlerMethod) handler;
            ResponseBody responseBody = hm.getMethod().getDeclaredAnnotation(ResponseBody.class);


            if (null == responseBody) {
                //说明是视图
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    mv.addObject("code", pe.getCode());
                    mv.addObject("msg", pe.getMsg());
                }
                //...
                return mv;
            } else {
                //反之是json
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("异常异常，请重试！");


                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                //...
                // 设置响应类型及编码格式（响应JSON格式的数据）
                response.setContentType("application/json;charset=UTF-8");
                // 得到字符输出流
                PrintWriter out = null;
                try {
                    // 得到输出流
                    out = response.getWriter();
                    // 将需要返回的对象转换成JOSN格式的字符
                    String json = JSON.toJSONString(resultInfo);
                    // 输出数据
                    out.write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 如果对象不为空，则关闭
                    if (out != null) {
                        out.close();
                    }
                }
                return null;
            }
        }

        return mv;
    }
}
