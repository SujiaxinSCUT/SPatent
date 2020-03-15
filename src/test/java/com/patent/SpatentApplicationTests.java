package com.patent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.patent.bean.Patent;
import com.patent.bean.ResultBuilder;
import com.patent.config.UploadConfig;
import com.patent.repository.IPatentRepository;
import com.patent.service.IConnectDaoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpatentApplicationTests {

//	@Autowired
//	UploadConfig config;
	@Autowired
	IPatentRepository ipr;
	@Autowired
	IConnectDaoService icd;
	
	@Test
	public void contextLoads() throws Exception {
         System.out.println(ipr.findById(1));
	}

}
