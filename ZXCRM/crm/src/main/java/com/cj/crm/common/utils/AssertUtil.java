package com.cj.crm.common.utils;


import com.cj.crm.common.exceptions.ParamsException;

public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
