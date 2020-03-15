package com.patent.bean;

import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLInsert;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "`Table`", uniqueConstraints = {@UniqueConstraint(columnNames="FAN")})
public class Patent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int PK;
	private int FAN;
	private String XPN;
	private String isPCT;
	private String isGD;
	private String title;
	private String abstruction;
	private String person;
	private String area;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date public_date;
	
	private String public_area;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date apply_date;
	
	private int apply_year;
	private String IPC;
	private int NPN;
	private String major;
	private String secondary;
	private String third;
	private String effect;
	private double score;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Timestamp upload_time;
	private int upload_num;
	
	
	public Patent() {
		this.score=0;
		upload_time = new Timestamp(System.currentTimeMillis());
	}
	
	

    



	public int getPK() {
		return PK;
	}







	public void setPK(int pK) {
		PK = pK;
	}







	public int getFAN() {
		return FAN;
	}







	public void setFAN(int fAN) {
		FAN = fAN;
	}







	public String getXPN() {
		return XPN;
	}







	public void setXPN(String xPN) {
		XPN = xPN;
	}







	public String getIsPCT() {
		return isPCT;
	}







	public void setIsPCT(String isPCT) {
		this.isPCT = isPCT;
	}







	public String getIsGD() {
		return isGD;
	}







	public void setIsGD(String isGD) {
		this.isGD = isGD;
	}







	public String getTitle() {
		return title;
	}







	public void setTitle(String title) {
		this.title = title;
	}







	public String getAbstruction() {
		return abstruction;
	}







	public void setAbstruction(String abstruction) {
		this.abstruction = abstruction;
	}







	public String getPerson() {
		return person;
	}







	public void setPerson(String person) {
		this.person = person;
	}







	public String getArea() {
		return area;
	}







	public void setArea(String area) {
		this.area = area;
	}







	public Date getPublic_date() {
		return public_date;
	}







	public void setPublic_date(Date public_date) {
		this.public_date = public_date;
	}







	public String getPublic_area() {
		return public_area;
	}







	public void setPublic_area(String public_area) {
		this.public_area = public_area;
	}







	public Date getApply_date() {
		return apply_date;
	}







	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}







	public int getApply_year() {
		return apply_year;
	}







	public void setApply_year(int apply_year) {
		this.apply_year = apply_year;
	}







	public String getIPC() {
		return IPC;
	}







	public void setIPC(String iPC) {
		IPC = iPC;
	}







	public int getNPN() {
		return NPN;
	}







	public void setNPN(int nPN) {
		NPN = nPN;
	}







	public String getMajor() {
		return major;
	}







	public void setMajor(String major) {
		this.major = major;
	}







	public String getSecondary() {
		return secondary;
	}







	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}







	public String getThird() {
		return third;
	}







	public void setThird(String third) {
		this.third = third;
	}







	public String getEffect() {
		return effect;
	}







	public void setEffect(String effect) {
		this.effect = effect;
	}







	public double getScore() {
		return score;
	}







	public void setScore(double score) {
		this.score = score;
	}







	public Timestamp getUpload_time() {
		return upload_time;
	}







	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}







	public int getUpload_num() {
		return upload_num;
	}







	public void setUpload_num(int upload_num) {
		this.upload_num = upload_num;
	}







	@Override
	public String toString() {
		return "Patent [PK=" + PK + ", FAN=" + FAN + ", XPN=" + XPN + ", isPCT=" + isPCT + ", isGD=" + isGD + ", title="
				+ title + ", abstruction=" + abstruction + ", person=" + person + ", area=" + area + ", public_date="
				+ public_date + ", public_area=" + public_area + ", apply_date=" + apply_date + ", apply_year="
				+ apply_year + ", IPC=" + IPC + ", NPN=" + NPN + ", major=" + major + ", secondary=" + secondary
				+ ", third=" + third + ", effect=" + effect + ", score=" + score + ", upload_time=" + upload_time
				+ ", upload_num=" + upload_num + "]";
	}

























	
	
	
}
