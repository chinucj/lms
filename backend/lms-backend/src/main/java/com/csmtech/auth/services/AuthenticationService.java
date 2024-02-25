package com.csmtech.auth.services;

import com.csmtech.auth.entities.UserMaster;

public interface AuthenticationService {

	UserMaster findByEmail(String email);

}
