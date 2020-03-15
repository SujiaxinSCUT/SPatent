package com.patent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patent.bean.ResultBuilder;
import com.patent.service.IConnectDaoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class PatentController {

	//日志实现
    Logger logger = LoggerFactory.getLogger(PatentController.class);
    @Autowired
    IConnectDaoService icd;
    
    @PostMapping(value = "/apply_yearServlet")
    @ResponseBody
    public JSONObject getApply_year(@RequestParam(name = "techArea") String major) {
    	
    	try {
			logger.info("开始查询申请年份记录数量");
			JSONArray data = icd.CountOfApplyYear(major);
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    }
    
    @PostMapping(value = "/public_yearServlet")
    @ResponseBody
    public JSONObject getPublic_year(@RequestParam(name = "techArea") String major) {
    	
    	try {
			logger.info("开始查询公开年份记录数量");
			JSONArray data = icd.CountOfPublicYear(major);
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    }
    
    @PostMapping(value = "/BatchServlet")
    @ResponseBody
    public JSONObject getBatch() {
    	
    	try {
			logger.info("开始查询批次信息");
			JSONArray data = icd.getBatch();
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    }
    
    @PostMapping(value = "/MajorServlet")
    @ResponseBody
    public JSONObject getMajor() {
    	
    	try {
			logger.info("开始查询领域信息");
			JSONArray data = icd.getMajor();
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    }
    
    @PostMapping(value = "/ZoneServlet")
    @ResponseBody
    public JSONObject getZone() {
    	
    	try {
			logger.info("开始查询地区信息");
			JSONArray data = icd.getZone();
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    }
    
    @PostMapping(value = "/deleteByBatch")
    @ResponseBody
    public JSONObject deleteByBatch(@RequestParam(name = "batch") int batch) {
    	
    	try {
			logger.info("开始删除第"+batch+"批次的数据");
			icd.deleteByBatch(batch);
			logger.info("删除成功");
			return ResultBuilder.create(200, "", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除失败");
			return ResultBuilder.create(e);
		}
    	
    }
    
    @PostMapping(value = "/deleteByMajor")
    @ResponseBody
    public JSONObject deleteByMajor(@RequestParam(name = "techArea") String major) {
    	
    	try {
			logger.info("开始删除"+major+"领域的数据");
			icd.deleteByMajor(major);
			logger.info("删除成功");
			return ResultBuilder.create(200, "", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除失败");
			return ResultBuilder.create(e);
		}
    	
    }
    
    @PostMapping(value = "/getTopTenServlet")
    @ResponseBody
    public JSONObject getTopTen(@RequestParam(name = "techArea") String major) {
    	
    	try {
			logger.info("开始查询评分前十记录");
			JSONArray data = icd.Search(major);
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    	
    }
    
    @PostMapping(value = "/indexServlet")
    @ResponseBody
    public JSONObject index(@RequestParam(name = "techArea", required=false) String major,
    		                @RequestParam(name = "beginYear", required=false) Integer beginYear,
    		                @RequestParam(name = "endYear", required=false) Integer endYear,
    		                @RequestParam(name = "area", required=false) String area,
    		                @RequestParam(name = "person", required=false) String person,
    		                @RequestParam(name = "title", required=false) String title,
    		                @RequestParam(name = "abstruction", required=false) String abstruction,
    		                @RequestParam("page") int page, @RequestParam("size")int size) {
    	
    	try {
    		Pageable pageable = PageRequest.of(page, size);
			logger.info("开始检索");			
			JSONObject data = icd.Search(beginYear, endYear, area, major, person, title, abstruction,pageable);
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    	
    }
    

    
    @PostMapping(value = "/personStaServlet")
    @ResponseBody
    public JSONObject getPersonSta(@RequestParam(name = "techArea") String major) {
    	
    	try {
			logger.info("开始查询领域个人专利记录");
			JSONArray data = icd.CountOfPerson(major);
			logger.info("查询成功");
			return ResultBuilder.create(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询失败");
			return ResultBuilder.create(e);
		}
    	
    }
}
