package com.patent.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class JSONResult implements Serializable{
    private static final long serialVersionUID = -3644950655568598241L;
 
    public static final int SUCCESS=200;
    public static final int ERROR=500;
 
    /** 
     * 返回是否成功的状态, 0表示成功, 
     * 1或其他值 表示失败
     */
    private int state;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String message;
 
 
    public JSONResult() {
    }
 
 
    public JSONResult(int state, Object data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }
 
    public JSONResult(Throwable e){
        state = ERROR;
        data="";
        message=e.getMessage();
    }
 
    public JSONResult(Object data){
        state = SUCCESS;
        this.data=data;
        message="";
    }
    
 
    public int getState() {
        return state;
    }
 
 
    public void setState(int state) {
        this.state = state;
    }
 
 
    public Object getData() {
        return data;
    }
 
 
    public void setData(Object data) {
        this.data = data;
    }
 
 
    public String getMessage() {
        return message;
    }
 
 
    public void setMessage(String message) {
        this.message = message;
    }


	@Override
	public String toString() {
		return "JSONResult [state=" + state + ", data=" + data + ", message=" + message + "]";
	}
 
 
    
 

}