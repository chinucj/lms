package com.csmtech.services;

import java.util.List;
import java.util.Map;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.dtos.UserRegistrationDto;

public interface UserMasterService {
	UserMaster saveUserMaster(UserRegistrationDto userMasterDto);

	List<Map<String, Object>> getUseMasterList();

	void deleteUserMaster(Integer userId);

	Map<String, Object> getUserMasterById(Integer userId);
}
