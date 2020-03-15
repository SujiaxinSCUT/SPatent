package com.patent.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.patent.bean.User;



public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User>,Serializable{

}
