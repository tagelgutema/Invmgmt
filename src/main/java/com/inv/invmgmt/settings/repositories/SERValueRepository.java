package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.SERValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SERValueRepository extends JpaRepository <SERValue,Integer>{
	@Query(value= "select s  from  SERValue s where " +
			"s.serMonth LIKE %?1%")
	List<SERValue> searchBySERValue(String keyword);

}
