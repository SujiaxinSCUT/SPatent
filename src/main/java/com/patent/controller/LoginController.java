package com.patent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patent.bean.JSONResult;
import com.patent.bean.ResultBuilder;
import com.patent.service.IConnectDaoService;

import net.sf.json.JSONObject;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    IConnectDaoService icd;
	
	@PostMapping(value = "/LoginServlet")
	@ResponseBody
    public JSONObject login(@RequestParam(name = "username" ) String username,
            @RequestParam(name = "password") String password, HttpServletRequest request) {
          try {
                 logger.info("请求登录");
                 JSONResult result = icd.CheckUser(username, password);
                 if(result.getState() == 200) {
                	 request.getSession().setAttribute("user", username);
                     return ResultBuilder.create(200,"","登录成功");
                 }else {
                     return ResultBuilder.create(new Exception(result.getMessage()));
                 }
              } catch (Exception e) {
                 // TODO Auto-generated catch block
                   e.printStackTrace();
                   return ResultBuilder.create(e);
              }

     }
	
	@PostMapping(value = "/CheckLogin")
	@ResponseBody
	public JSONObject checkLogin(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null) {
			return ResultBuilder.create(200,"","已登录");
		}else {
			return ResultBuilder.create(500,"","未登录");
		}
	}
}
