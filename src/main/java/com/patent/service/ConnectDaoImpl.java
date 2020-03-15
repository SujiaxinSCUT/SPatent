package com.patent.service;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.patent.bean.JSONResult;
import com.patent.bean.Patent;
import com.patent.bean.Record;
import com.patent.bean.ResultBuilder;
import com.patent.bean.User;
import com.patent.controller.PatentController;
import com.patent.repository.IPatentRepository;
import com.patent.repository.IRecordRepository;
import com.patent.repository.UserRepository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

@Component
public class ConnectDaoImpl implements IConnectDaoService {

    @Autowired
	IPatentRepository prs;
    @Autowired
    IRecordRepository ipr;
    @Autowired
    UserRepository	urp;
    Logger logger = LoggerFactory.getLogger(ConnectDaoImpl.class);
    JsonConfig jsonConfig;
	public ConnectDaoImpl() {
		// TODO Auto-generated constructor stub
		super();
	    jsonConfig=new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            @Override
	            public Object processArrayValue(Object o, JsonConfig jsonConfig) {
	                return simpleDateFormat.format(o);
	            }

	            @Override
	            public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
	                if (o != null) {
	                    return simpleDateFormat.format(o);
	                } else {
	                    return null;
	                }
	            }
	        });
	}
	
	@Override
	public void Insert(ArrayList<Patent> patents) {
		// TODO Auto-generated method stub
		for(Patent patent:patents) {
			List<Patent> p = prs.findByFAN(patent.getFAN());
			if(p.size() != 0) {
				prs.delete(p.get(0));
			}
			prs.save(patent);
		}
		logger.info("记录保存成功");
	}

	@Override
	public JSONObject Search(Integer beginYear, Integer endYear, String area, String major, String person, String title,
			String abstruction,Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Patent> patents = prs.Search(beginYear, endYear, area, major, person, title, abstruction,pageable);
		JSONObject obj = new JSONObject();
		obj.put("total_page", patents.getTotalPages());
		obj.put("total_elements", patents.getTotalElements());
		obj.put("page_content",JSONArray.fromObject(patents.getContent(),jsonConfig));

		return obj;
	}

	@Override
	public JSONArray CountOfApplyYear(String major) {
		// TODO Auto-generated method stub
		List<Object[]> lists =prs.getCountByApplyYear(major);
		JSONArray array = new JSONArray();
		for(Object[] list:lists) {
			JSONObject o = new JSONObject();
			o.put("year", list[0]);
			o.put("count", list[1]);
			array.add(o);
		}
		return array;
	}

	@Override
	public JSONArray CountOfPublicYear(String major) {
		// TODO Auto-generated method stub
		List<Object[]> lists =prs.getCountByPublicYear(major);
		JSONArray array = new JSONArray();
		for(Object[] list:lists) {
			JSONObject o = new JSONObject();
			o.put("year", list[0]);
			o.put("count", list[1]);
			array.add(o);
		}
		return array;
	}

	@Override
	public JSONArray Search(String major) {
		// TODO Auto-generated method stub
		List<Object[]> lists =prs.findByMajorOrderbyScore(major);
		JSONArray array = new JSONArray();
		for(Object[] list:lists) {
			JSONObject o = new JSONObject();
			o.put("title", list[0]);
			o.put("score", list[1]);
			array.add(o);
		}
		return array;
	}

	@Override
	public JSONArray CountOfPerson(String major) {
		// TODO Auto-generated method stub
		List<Object[]> lists =prs.getCountOfPerson(major);
		JSONArray array = new JSONArray();
		int total = 0;
		for(Object[] list:lists) {
			JSONObject o = new JSONObject();
			o.put("person", list[0]);
			o.put("count", list[1]);
			BigInteger bi = (BigInteger)list[1];
			total += bi.intValue();
			array.add(o);
		}
		long totalCount = prs.count();
		JSONObject o = new JSONObject();
		o.put("person", "其他");
		o.put("count", totalCount - total);
		array.add(o);
		return array;
	}

	@Override
	public JSONResult CheckUser(String username, String password){
		// TODO Auto-generated method stub
		List<User> users = urp.findAll();
		for(User u : users) {
			if (username.equals(u.getUsername())) {				
                if(!password.equals(u.getPassword())){
				return new JSONResult(new Exception("密码错误"));
			    }else {
				return new JSONResult(200, "", "");
			    }
		     }
		}
		return new JSONResult(new Exception("该用户不存在"));
	}

	@Override
	public void deleteByBatch(int batch){
		// TODO Auto-generated method stub
		prs.deleteByBatch(batch);
	}

	@Override
	public void deleteByMajor(String major) {
		// TODO Auto-generated method stub
		prs.deleteByMajor(major);
	}

	@Override
	public JSONArray getMajor(){
		// TODO Auto-generated method stub
		return JSONArray.fromObject(prs.getMajor());
	}


	@Override
	public JSONArray getZone(){
		// TODO Auto-generated method stub
		return JSONArray.fromObject(prs.getZone());
	}

	@Override
	public Integer getRecord(){
		// TODO Auto-generated method stub
		Optional<Record> recordOp = ipr.findById(1);	
		Record record = recordOp.get();
		int upload_num = record.getUpload_num();
		int new_num = upload_num + 1;
		record.setUpload_num(new_num);
		return upload_num;
	}

	@Override
	public JSONArray getBatch() {
		// TODO Auto-generated method stub
		List<Object[]> lists =prs.getBatch();
		JSONArray array = new JSONArray();
		for(Object[] list:lists) {
			JSONObject o = new JSONObject();
			o.put("batch", list[0]);
			o.put("count", list[1]);
			o.put("time",list[2].toString());
			array.add(o);
		}
		return array;
	}

}
