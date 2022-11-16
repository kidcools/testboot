package com.kidcools.testboot.utils;

public class ResponseHelper {
    public static ResponseVo success(Object data){
        return new ResponseVo(true,0,"操作成功",data);
    }
}
