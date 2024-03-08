package com.csmtech.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.auth.repositories.UserMasterRepository;
import com.csmtech.dtos.UserRegistrationDto;
import com.csmtech.utils.EmailSender;
import com.csmtech.utils.PasswordGenerator;


@Service
public class UserMasterServiceImpl implements UserMasterService {
	
	private static final Logger log = LoggerFactory.getLogger(UserMasterServiceImpl.class);
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@Override
	public UserMaster saveUserMaster(UserRegistrationDto userMasterDto) {

		UserMaster userMaster = new UserMaster();
		userMaster.setFullName(userMasterDto.getFullName());
		userMaster.setDesignationId(userMasterDto.getDesignationId());
		userMaster.setContactNumber(userMasterDto.getContactNumber());
		userMaster.setEmail(userMasterDto.getEmailId());
		userMaster.setLocation(userMasterDto.getLocation());
		userMaster.setDeletedFlag(false);
		if (userMasterDto.getUserId() == 0) {
			userMaster.setCreatedBy(1);
			userMaster.setCreatedOn(new Date());
			String generatedPassword = PasswordGenerator.generateRandomPassword(10);
			userMaster.setPassword(generatedPassword);
			// Send password via email
			log.info("Password created");
			EmailSender emailSender = new EmailSender();
			emailSender.sendPasswordByEmail(userMasterDto.getEmailId(), generatedPassword);
		}
		else {
			userMaster.setUserId(userMasterDto.getUserId());
			userMaster.setUpdatedBy(1);
			userMaster.setUpdatedOn(new Date());
		}
		return userMasterRepository.save(userMaster);

	}

	@Override
	public List<Map<String, Object>> getUseMasterList() {
		return userMasterRepository.getUseMasterList();
	}

	@Override
	public void deleteUserMaster(Integer userId) {
		userMasterRepository.deleteUserMaster(userId);
	}

	@Override
	public Map<String, Object> getUserMasterById(Integer userId) {
		return userMasterRepository.getUserMasterById(userId);
	}
}
