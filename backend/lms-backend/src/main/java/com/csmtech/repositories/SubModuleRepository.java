package com.csmtech.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csmtech.entities.SubModule;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModule, Integer> {

	@Query(value = "select SUB_MODULE_ID,SUB_MODULE_NAME from sub_module where MODULE_ID=2", nativeQuery = true)
	List<Map<String, Object>> getSubmoduleList();

}
