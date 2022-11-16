package com.kidcools.testboot.utils;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {

    /**
    * 
    */
   private static final long serialVersionUID = 5436311696304320083L;

   /**
     * 是否成功标志。
     * 需要特别说明的是，如果返回处理中，则此处为false
     */
    private boolean isSuccess;
    
    /**
     * 扩展属性
     */
    private String extraAttr;
    

    private int errorCode;
    
    /**
     * 错误返回说明
     */
    private String msg;
    
    /**
     * 返回的实体类
     */
    private T data;
    
    /**
     * 分页总条数
     */
    private long totalProperty;
    
  


   public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseVo<T> getSuccessResponse(T obj){
        return new ResponseVo<T>(obj);
    }
    public static <T> ResponseVo<T> getSuccessResponse(){
       
        return new ResponseVo<T>();
    }
    public static <T> ResponseVo<T> getSuccessResponse(long totalProperty,T obj){
        return new ResponseVo<T>(totalProperty,obj);
    }
    public static <T> ResponseVo<T> getErrorResponse(){
        return new ResponseVo<T>(false,-1000,null);
    }
    public static <T> ResponseVo<T> getErrorResponse(String msg){
        return new ResponseVo<T>(false,-1000, msg);
    }
    public static <T> ResponseVo<T> getErrorResponse(int errorCode,String msg){
        return new ResponseVo<T>(false,errorCode,msg);
    }
    public ResponseVo() {
        super();
        this.isSuccess = true;
        this.errorCode=0;
        this.data=null;
    }
    
    public ResponseVo(T obj) {
        super();
        this.isSuccess = true;
        this.errorCode=0;
        this.data=obj;
    }
    
    public ResponseVo (long totalProperty,T obj) {
        super();
        this.isSuccess = true;
        this.errorCode=0;
        this.data=obj;
        this.totalProperty = totalProperty;
    }

    public ResponseVo(boolean isSuccess, int errorCode, String msg) {
        super();
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.msg = msg;
    }
    public ResponseVo(boolean isSuccess, int errorCode, String msg, T date) {
        super();
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = date;
    }
    
    public ResponseVo<T> setDataObj(T date){
        this.setData(date);
        return this;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getExtraAttr() {
      return extraAttr;
   }


   public ResponseVo<T> setExtraAttr(String extraAttr) {
      this.extraAttr = extraAttr;
      return this;
   }


   public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
    public long getTotalProperty() {
        return totalProperty;
    }


    public void setTotalProperty(long totalProperty) {
        this.totalProperty = totalProperty;
    }

}