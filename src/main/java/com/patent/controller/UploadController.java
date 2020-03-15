package com.patent.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.patent.bean.JSONResult;
import com.patent.bean.Patent;
import com.patent.bean.ResultBuilder;
import com.patent.config.UploadConfig;
import com.patent.service.IConnectDaoService;
import com.patent.service.IReadExcelService;
import com.patent.service.ReadExcelImpl;

import net.sf.json.JSONObject;

@Controller
public class UploadController {
	 
    
    //日志实现
    Logger logger = LoggerFactory.getLogger(UploadController.class);
    //读取工具类
    IReadExcelService irs;
    @Autowired
    IConnectDaoService icd;
    
    @PostMapping(value = "/upLoadServlet")
    @ResponseBody
	public JSONObject uploadFile(MultipartHttpServletRequest request) {
		//返回json
		
		List<MultipartFile> files = request.getFiles("file");
//		if(!ServletFileUpload.isMultipartContent(request))
//		 {
//			 result = new JSONResult(new Exception("Error:表单必须包含enctype=multipart/form-data"));
//			 return result;
//		 }
//		 DiskFileItemFactory factory=new DiskFileItemFactory();
//		 factory.setSizeThreshold(UploadConfig.MEMORY_THRESHOLD);
//		 factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//		 ServletFileUpload upload=new ServletFileUpload(factory);
//		 
//		 upload.setFileSizeMax(UploadConfig.MAX_FILE_SIZE);
//		 
//		 upload.setSizeMax(UploadConfig.MAX_REQUEST_SIZE);
//		 
//		 upload.setHeaderEncoding("UTF-8");
//		 
         File uploadDir=new File(UploadConfig.UPLOAD_PATH);
		 
		 if(!uploadDir.exists()) {
			 uploadDir.mkdir();
		 }
//		 
//

//	            // 解析请求的内容提取文件数据
//	            @SuppressWarnings("unchecked")
//	            List<FileItem> formItems = upload.parseRequest(request);
//	            logger.info(String.valueOf(formItems.size()));
//	            if (formItems != null && formItems.size() > 0) {
//	                // 迭代表单数据
//	                for (FileItem item : formItems) {
//	                    // 处理不在表单中的字段
//	                    if (!item.isFormField()) {
//	                        String fileName = System.currentTimeMillis()+new File(item.getName()).getName();
//	                        String filePath =UploadConfig.UPLOAD_PATH + File.separator + fileName;
//	                        File storeFile = new File(filePath);
//	                        // 在控制台输出文件的上传路径
//	                        logger.info("文件已保存:"+filePath);
//	                        // 保存文件到硬盘
//	                        item.write(storeFile);
//
//	                        
//	                    }
//	                }
//	            }

			 for (int i = 0; i < files.size(); i++) {
			        MultipartFile file = files.get(i);
			        String fileName = file.getOriginalFilename();

			        String path = UploadConfig.UPLOAD_PATH +File.separator+ System.currentTimeMillis()+fileName;
			        File dest = new File(path);
			        try {
			            file.transferTo(dest);
			            logger.info("第" + (i + 1) + "个文件上传成功");
			            irs = new ReadExcelImpl();
			            ArrayList<Patent> patents = irs.ReadExcel(path,icd.getRecord());
			            icd.Insert(patents);
			        } catch (Exception e) {
			            logger.error(e.toString(), e);
			            e.printStackTrace();

							return ResultBuilder.create(new Exception("上传第" + (i++) + "个文件失败"));

			        }
			    }
			 return ResultBuilder.create(200, "", "文件上传成功");


           
		 
	
    }
}
