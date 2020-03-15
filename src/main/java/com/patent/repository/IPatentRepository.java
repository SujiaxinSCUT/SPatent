package com.patent.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.patent.bean.Patent;

public interface IPatentRepository extends JpaRepository<Patent,Integer>,JpaSpecificationExecutor<Patent>,Serializable{


	@Query(value = "select * from patent_manager.`Table` where if(?1 !='', apply_year>=?1,1=1) and "
			+ "if(?2 !='', apply_year<=?2,1=1) and if(?3 !='', area=?3,1=1) and if(?4 != '', major=?4,1=1) and "
			+ " if(?5 !='', person like %?5%,1=1) and if(?6 !='', title like %?6%,1=1) and if(?7 !='', abstruction"
			+ " like %?7% ,1=1)" , nativeQuery=true)
	Page<Patent> Search(Integer beginYear, Integer endYear,
			String area, String major, String person, String title, String abstruction,Pageable pageable);

	
	
	@Query(value= "select person,count(title) from  patent_manager.`Table` where person in" + 
			"(select distinct person  from patent_manager.`Table` where major=?1) and major =?1"
			+ " group by person "
			+ "order by count(title) desc limit 10",nativeQuery=true)
	List<Object[]> getCountOfPerson(String major);
	
	
	
	@Query(value = "select distinct major from patent_manager.`Table`", nativeQuery=true)
	List<String> getMajor();
	
	@Query(value = "select distinct area from patent_manager.`Table`", nativeQuery=true)
	List<String> getZone();
	
	@Query(value = "select distinct upload_num,count(PK),upload_time"
			+ " from patent_manager.`Table` group by upload_num;", nativeQuery=true)
	List<Object[]> getBatch();
	
		
	@Query(value = "select apply_year as year, count(PK) as count from patent_manager.`Table` where major = ? " + 
			"group by apply_year order by apply_year desc limit 50 ",
			nativeQuery=true)
	List<Object[]> getCountByApplyYear(String major);
	
	
	@Query(value = "select date_format(public_date,'%Y') as year , count(PK) as count from patent_manager.`Table` where major =? " + 
			"group by date_format(public_date,'%Y') order by date_format(public_date,'%Y') desc limit 50",
			nativeQuery=true)
	List<Object[]> getCountByPublicYear(String major);
	
	
	@Query(value = "select title,score from patent_manager.`Table` where  score <> 0 and major =?1 order by score desc limit 10 ",
			nativeQuery=true)
	List<Object[]> findByMajorOrderbyScore(String major);
	
	@Modifying
    @Transactional
	@Query(value = "delete from patent_manager.`Table` where major =?1", nativeQuery = true)
	void deleteByMajor(String major);
	
	@Modifying
    @Transactional
	@Query(value = "delete from patent_manager.`Table` where upload_num =?1", nativeQuery = true)
	void deleteByBatch(Integer upload_num);
	
	@Query(value = "select * from patent_manager.`Table` where FAN =?1",nativeQuery = true)
	List<Patent> findByFAN(int FAN);
	
}
