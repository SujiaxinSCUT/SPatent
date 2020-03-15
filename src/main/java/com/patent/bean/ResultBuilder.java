package com.patent.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

public class ResultBuilder {


	
	public static JSONObject create(int state, Object data, String message) {
    	JSONResult result=new JSONResult(state, data, message);
        JSONObject obj = JSONObject.fromObject(result);
        return obj;
    }
    
    public static JSONObject create(Throwable e) {
    	JSONResult result=new JSONResult(e);
        JSONObject obj = JSONObject.fromObject(result);
        return obj;
    }
    
    public static JSONObject create(Object data) {
    	JSONResult result=new JSONResult(data);
        JSONObject obj = JSONObject.fromObject(result);
        return obj;
    }
}
