package com.patent.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.patent.bean.Record;



public interface IRecordRepository extends JpaRepository<Record,Integer>,JpaSpecificationExecutor<Record>,Serializable{

	
}
