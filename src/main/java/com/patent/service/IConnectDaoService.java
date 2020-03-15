package com.patent.service;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;

import com.patent.bean.JSONResult;
import com.patent.bean.Patent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface IConnectDaoService {

    public void Insert(ArrayList<Patent> patents) throws Exception;
	
	public JSONObject Search(Integer beginYear,Integer endYear,String area,
			String major,String person,String title,String abstruction,Pageable pageable) throws Exception;

	public JSONArray CountOfApplyYear(String major) throws Exception;

	public JSONArray CountOfPublicYear(String major) throws Exception ;
	
	public JSONArray Search(String major) throws Exception;
	
	public JSONArray CountOfPerson(String major) throws Exception;
	
	public JSONResult CheckUser(String username,String password) throws Exception;
	
	public void deleteByBatch(int batch) throws Exception;
	
	public void deleteByMajor(String major) throws Exception;
	
	public JSONArray getMajor() throws Exception;
	
	public JSONArray getZone() throws Exception;
	
	public Integer getRecord() throws Exception;
	
	public JSONArray getBatch() throws Exception;
	
}
