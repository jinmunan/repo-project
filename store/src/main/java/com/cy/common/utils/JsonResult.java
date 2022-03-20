package com.cy.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 响应的类
 * Created by Jinmunan
 * 2022/3/18
 * 17:09
 */

/**JSon格式的数据进行相应*/

public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 数据
     */
    private E Data;


    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

//    public JsonResult(Integer state, String message) {
//        this.state = state;
//        this.message = message;
//    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        Data = data;
    }

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        Data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return Data;
    }

    public void setData(E data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", Data=" + Data +
                '}';
    }
}
