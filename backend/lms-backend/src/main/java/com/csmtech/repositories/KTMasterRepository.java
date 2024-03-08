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

	@Query(value="select kt.KT_ID,kt.KT_NAME,sm.SUB_MODULE_ID,sm.SUB_MODULE_NAME,kt.KT_FORMAT,kt.KT_PATH,pm.PROJECT_ID, pm.PROJECT_NAME,dm.DESGN_ID,dm.DESGN_NAME from kt_master kt\r\n"
			+ "	join sub_module sm on kt.SUB_MODULE_ID=sm.SUB_MODULE_ID\r\n"
			+ "	join project_master pm on  kt.PROJECT_ID=pm.PROJECT_ID\r\n"
			+ "	left join designation_master dm on kt.DESGN_ID=dm.DESGN_ID  where kt.DELETED_FLAG=0",nativeQuery = true)
			List<Map<String, Object>> getgetKTDetails();

			 
			@Modifying
			@Transactional
			@Query(value="update kt_master set DELETED_FLAG=1 where KT_ID=:ktId", nativeQuery=true)
			void deleteKT(Integer ktId);


			@Query(value="select kt.KT_ID,kt.CREATED_ON,kt.KT_NAME,sm.SUB_MODULE_ID,sm.SUB_MODULE_NAME,kt.KT_FORMAT,kt.KT_PATH,pm.PROJECT_ID, pm.PROJECT_NAME,dm.DESGN_ID,dm.DESGN_NAME from kt_master kt\r\n"
			+ "join sub_module sm on kt.SUB_MODULE_ID=sm.SUB_MODULE_ID\r\n"
			+ "join project_master pm on  kt.PROJECT_ID=pm.PROJECT_ID\r\n"
			+ "left join designation_master dm on kt.DESGN_ID=dm.DESGN_ID where kt.KT_ID=:ktId",nativeQuery = true)
			Map<String, Object> getKTById(Integer ktId);

}
