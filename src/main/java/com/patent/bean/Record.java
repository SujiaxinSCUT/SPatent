package com.patent.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private int upload_num;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUpload_num() {
		return upload_num;
	}

	public void setUpload_num(int upload_num) {
		this.upload_num = upload_num;
	}

	@Override
	public String toString() {
		return "Record [Id=" + Id + ", upload_num=" + upload_num + "]";
	}
	
	
}
