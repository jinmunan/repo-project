package com.cj.crm.common.base;

public class ResultInfo {
    private Integer code = 200;//响应码
    private String msg = "success";//响应信息
    private Object data;//响应数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
