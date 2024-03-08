package com.csmtech.auth.repositories;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csmtech.auth.entities.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

	UserMaster findByEmail(String email);

	@Query(value = "select um.INT_USER_ID,um.VCH_FULL_NAME,dm.VCH_DESGN_NAME,um.VCH_CONTACT_NO,um.VCH_EMAIL_ID,um.VCH_LOCATION from user_master um\r\n"
			+ "join designation_master dm on um.INT_DESGN_ID=dm.INT_DESGN_ID where um.BIT_DELETED_FLAG=0 and um.INT_USER_ID <> 1", nativeQuery = true)
	List<Map<String, Object>> getUseMasterList();

	@Modifying
	@Transactional
	@Query(value = "update user_master set BIT_DELETED_FLAG=1 where INT_USER_ID=:userId", nativeQuery = true)
	void deleteUserMaster(Integer userId);

	@Query(value = "select um.INT_USER_ID,um.VCH_FULL_NAME,dm.INT_DESGN_ID,dm.VCH_DESGN_NAME,um.VCH_CONTACT_NO,um.VCH_EMAIL_ID,um.VCH_LOCATION from user_master um\r\n"
			+ "join designation_master dm on um.INT_DESGN_ID=dm.INT_DESGN_ID where um.INT_USER_ID=:userId", nativeQuery = true)
	Map<String, Object> getUserMasterById(Integer userId);
}
