package com.lcworld.exception;

public class ZHHQException extends RuntimeException{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String msg ;
    private int errCode;

   
    public ZHHQException(int errorCode,String errMsg){
        super();
        this.errCode=errorCode;
        this.msg = errMsg;
    }


  
    public String getMsg() {
        return msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }



    public int getErrCode() {
        return errCode;
    }



    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }



    public String toString(){
        return "errorCode:"+errCode+"errorMsg : "+ msg;
    }
    
}
