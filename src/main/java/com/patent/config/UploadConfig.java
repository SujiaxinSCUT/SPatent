package com.patent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "uploadconfig")
public class UploadConfig {

	// 上传配置    
//	public static int MEMORY_THRESHOLD ;
//		 
//	public static int MAX_FILE_SIZE ;
//
//	public static int MAX_REQUEST_SIZE ;

    public static String UPLOAD_PATH;



//    @Value("${uploadconfig.MEMORY_THRESHOLD}")
//	public void setMEMORY_THRESHOLD(int mEMORY_THRESHOLD) {
//		MEMORY_THRESHOLD = mEMORY_THRESHOLD;
//	}
//
//
//    @Value("${uploadconfig.MAX_FILE_SIZE}")
//	public void setMAX_FILE_SIZE(int mAX_FILE_SIZE) {
//		MAX_FILE_SIZE = mAX_FILE_SIZE;
//	}
//
//
//    @Value("${uploadconfig.MAX_REQUEST_SIZE}")
//	public void setMAX_REQUEST_SIZE(int mAX_REQUEST_SIZE) {
//		MAX_REQUEST_SIZE = mAX_REQUEST_SIZE;
//	}


    @Value("${uploadconfig.UPLOAD_PATH}")
	public void setUPLOAD_PATH(String uPLOAD_PATH) {
		UPLOAD_PATH = uPLOAD_PATH;
	}

    
		
	    
}
