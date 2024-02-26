package com.csmtech.repositories;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csmtech.entities.KTMaster;

@Repository
public interface KTMasterRepository extends JpaRepository<KTMaster, Integer> {

	@Query(value = "select kt.INT_KT_ID,kt.VCH_KT_NAME,kt.VCH_KT_TYPE,kt.VCH_KT_FORMAT,kt.VCH_KT_PATH, pm.VCH_PROJECT_NAME from kt_master kt\r\n"
			+ "join project_master pm on  kt.INT_PROJECT_ID=pm.INT_PROJECT_ID where kt.BIT_DELETED_FLAG=0", nativeQuery = true)
	List<Map<String, Object>> getgetKTDetails();

	@Modifying
	@Transactional
	@Query(value = "update kt_master set BIT_DELETED_FLAG=1 where INT_KT_ID=:ktId", nativeQuery = true)
	void deleteKT(Integer ktId);

	@Query(value = "select kt.INT_KT_ID,kt.VCH_KT_NAME,kt.VCH_KT_TYPE,kt.VCH_KT_FORMAT,kt.VCH_KT_PATH,pm.INT_PROJECT_ID, pm.VCH_PROJECT_NAME from kt_master kt\r\n"
			+ "join project_master pm on  kt.INT_PROJECT_ID=pm.INT_PROJECT_ID where kt.INT_KT_ID=:ktId", nativeQuery = true)
	Map<String, Object> getKTById(Integer ktId);

}
