package com.csmtech.auth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csmtech.auth.entities.UserMaster;
import com.csmtech.auth.repositories.UserMasterRepository;
import com.csmtech.auth.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("${lms.bcryptpassword.secretKey}")
	private String secretkey;

	@Autowired
	private UserMasterRepository repo;

	@Override
	public UserMaster findByEmail(String email) {
		return repo.findByEmail(email);
	}

}
